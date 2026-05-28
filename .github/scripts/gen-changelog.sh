#!/bin/bash
set -euo pipefail

OUTPUT="CHANGELOG.md"
REPO_ROOT="$(cd "$(dirname "$0")/../.." && pwd)"
OUTPUT="$REPO_ROOT/$OUTPUT"

cat > "$OUTPUT" << 'HEADER'
# Changelog

> 本文档由 CI 自动生成，记录所有已合并到 main 分支的变更。请勿手动编辑。

HEADER

git log main --reverse --format="%H" | while read -r hash; do

    short_hash=$(git log -1 --format="%h" "$hash")
    author=$(git log -1 --format="%an" "$hash")
    date=$(git log -1 --format="%ad" --date=short "$hash")
    subject=$(git log -1 --format="%s" "$hash")
    parents=$(git log -1 --format="%P" "$hash")

    parent_count=$(echo "$parents" | wc -w)
    is_merge=false
    is_root=false
    [ "$parent_count" -gt 1 ] && is_merge=true
    [ "$parent_count" -eq 0 ] && is_root=true

    {
        echo ""
        echo "---"
        echo ""
        echo "## $date — $subject"
        echo ""
        echo "**\`$short_hash\`** | $author"
        echo ""
    } >> "$OUTPUT"

    if $is_merge; then
        echo "*合并提交，变更细节见各子提交。*" >> "$OUTPUT"
        continue
    fi

    # --- Changed files ---
    echo "### 改动文件" >> "$OUTPUT"
    echo "" >> "$OUTPUT"
    echo '| 文件 | + | - |' >> "$OUTPUT"
    echo '|------|---|---|' >> "$OUTPUT"

    numstat=$(git -c core.quotepath=false diff-tree --no-commit-id --numstat -r "$hash" 2>/dev/null || \
              git -c core.quotepath=false diff-tree --no-commit-id --numstat --root -r "$hash" 2>/dev/null || true)

    if [ -n "$numstat" ]; then
        echo "$numstat" | while read -r added deleted file; do
            [ -n "$file" ] || continue
            echo "| \`$file\` | $added | $deleted |" >> "$OUTPUT"
        done
    else
        echo "| *无文件变更* | | |" >> "$OUTPUT"
    fi

    # --- Changed classes/methods ---
    echo "" >> "$OUTPUT"
    echo "### 改动位置" >> "$OUTPUT"
    echo "" >> "$OUTPUT"

    if $is_root; then
        echo "*初始提交，全部新增。*" >> "$OUTPUT"
    else
        diff_output=$(git -c core.quotepath=false diff-tree --no-commit-id -p -r "$hash" 2>/dev/null || true)

        contexts=$(echo "$diff_output" | grep "^@@" | \
                   sed -n 's/.*@@[^@]*@@[[:space:]]*\(.*\)/\1/p' | \
                   sed '/^$/d' | \
                   grep -v "^import " | \
                   grep -v "^package " | \
                   grep -v "^[a-z][a-z]*:$" | \
                   sort -u || true)

        if [ -n "$contexts" ]; then
            echo "$contexts" | while read -r ctx; do
                echo "- $ctx" >> "$OUTPUT"
            done
        else
            echo "*无具体位置信息。*" >> "$OUTPUT"
        fi
    fi

done

{
    echo ""
    echo "---"
    echo ""
    echo "*自动生成于 $(date -u +"%Y-%m-%d %H:%M:%S UTC")*"
    echo ""
} >> "$OUTPUT"
