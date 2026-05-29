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

        echo "已加载 launchd 任务，每 10 分钟自动同步 doc/CHANGELOG.md。"
        echo "检查状态: launchctl list | grep homepage"
        ;;

    Linux)
        echo "检测到 Linux，使用 crontab 注册定时任务..."
        CRON_LINE="*/10 * * * * /bin/bash $SYNC_SCRIPT"

        # Remove existing entry first
        (crontab -l 2>/dev/null | grep -v "$SYNC_SCRIPT"; echo "$CRON_LINE") | crontab -

        echo "已添加 crontab 条目，每 10 分钟自动同步 doc/CHANGELOG.md。"
        echo "检查状态: crontab -l"
        ;;

    MINGW*|MSYS*|CYGWIN*)
        echo "检测到 Windows (Git Bash)，使用 schtasks 注册定时任务..."

        # 动态获取 bash.exe 的 Windows 路径
        BASH_WIN=$(cygpath -w "$(which bash)" 2>/dev/null)
        if [ -z "$BASH_WIN" ]; then
            # 回退：查找常见安装位置
            for candidate in \
                "C:\\Program Files\\Git\\usr\\bin\\bash.exe" \
                "C:\\Program Files (x86)\\Git\\usr\\bin\\bash.exe" \
                "C:\\Git\\usr\\bin\\bash.exe"; do
                if [ -f "$(cygpath -u "$candidate" 2>/dev/null)" ] 2>/dev/null; then
                    BASH_WIN="$candidate"
                    break
                fi
            done
        fi

        if [ -z "$BASH_WIN" ]; then
            echo "错误: 找不到 bash.exe，请确认 Git Bash 已安装。"
            exit 1
        fi

        echo "bash.exe 路径: $BASH_WIN"

        # 获取项目根目录的 Unix 路径（供 bash -c 使用）
        UNIX_ROOT="$(cd "$REPO_ROOT" && pwd)"

        # 生成 VBS 包装脚本（静默执行，不弹窗）
        # 使用 Chr(34) 即双引号，避免嵌套转义混乱
        VBS_FILE="$SCRIPT_DIR/sync-changelog.vbs"
        cat > "$VBS_FILE" << VBS_EOF
Dim q: q = Chr(34)
CreateObject("WScript.Shell").Run q & "$BASH_WIN" & q & " -l -c " & q & "cd '$UNIX_ROOT' && bash '$SYNC_SCRIPT'" & q, 0, False
VBS_EOF

        echo "已生成 VBS 包装脚本: $VBS_FILE"

        # 移除旧任务（如果存在）
        schtasks //delete //tn "Sync Changelog" //f 2>/dev/null || true

        # 创建计划任务，指向 VBS（VBS 以 wscript.exe 运行即可无窗口）
        schtasks //create //tn "Sync Changelog" \
            //tr "wscript.exe \"$VBS_FILE\"" \
            //sc minute //mo 10 //f

        echo "已创建 Windows 计划任务，每 10 分钟自动同步 doc/CHANGELOG.md（静默后台执行）。"
        echo "检查状态: schtasks //query //tn \"Sync Changelog\""
        ;;

    *)
        echo "未知操作系统，无法自动安装。请手动运行: bash $SYNC_SCRIPT"
        exit 1
        ;;
esac

echo ""
echo "安装完成。你可以手动运行一次验证: bash $SYNC_SCRIPT"
