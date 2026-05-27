#!/bin/bash
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "$0")/../.." && pwd)"
SCRIPT_DIR="$REPO_ROOT/.github/scripts"
SYNC_SCRIPT="$SCRIPT_DIR/sync-changelog.sh"

echo "=== Changelog 自动同步 - 安装脚本 ==="
echo "项目路径: $REPO_ROOT"
echo ""

case "$(uname -s)" in
    Darwin)
        echo "检测到 macOS，使用 launchd 注册定时任务..."

        PLIST_SRC="$SCRIPT_DIR/com.homepage.sync-changelog.plist"
        PLIST_DST="$HOME/Library/LaunchAgents/com.homepage.sync-changelog.plist"

        # Replace placeholder with actual path
        sed "s|/Users/REPLACE_USERNAME/Desktop/HomePage|$REPO_ROOT|g" "$PLIST_SRC" > "$PLIST_DST"

        # Unload if already loaded
        launchctl unload "$PLIST_DST" 2>/dev/null || true

        # Load
        launchctl load "$PLIST_DST"

        echo "已加载 launchd 任务，每 10 分钟自动同步 CHANGELOG.md。"
        echo "检查状态: launchctl list | grep homepage"
        ;;

    Linux)
        echo "检测到 Linux，使用 crontab 注册定时任务..."
        CRON_LINE="*/10 * * * * /bin/bash $SYNC_SCRIPT"

        # Remove existing entry first
        (crontab -l 2>/dev/null | grep -v "$SYNC_SCRIPT"; echo "$CRON_LINE") | crontab -

        echo "已添加 crontab 条目，每 10 分钟自动同步 CHANGELOG.md。"
        echo "检查状态: crontab -l"
        ;;

    MINGW*|MSYS*|CYGWIN*)
        echo "检测到 Windows (Git Bash)，使用 schtasks 注册定时任务..."

        BAT_SCRIPT="$SCRIPT_DIR/sync-changelog.bat"

        schtasks //create //tn "Sync Changelog" \
            //tr "$BAT_SCRIPT" \
            //sc minute //mo 10 //f 2>/dev/null || \
            schtasks //create //tn "Sync Changelog" \
                //tr "$(cygpath -w "$BAT_SCRIPT")" \
                //sc minute //mo 10 //f

        echo "已创建 Windows 计划任务，每 10 分钟自动同步 CHANGELOG.md。"
        echo "检查状态: schtasks //query //tn \"Sync Changelog\""
        ;;

    *)
        echo "未知操作系统，无法自动安装。请手动运行: bash $SYNC_SCRIPT"
        exit 1
        ;;
esac

echo ""
echo "安装完成。你可以手动运行一次验证: bash $SYNC_SCRIPT"
