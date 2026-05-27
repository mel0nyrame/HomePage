#!/bin/bash
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$REPO_ROOT"

git fetch origin main

LOCAL_HASH=$(git hash-object CHANGELOG.md 2>/dev/null || echo "")
REMOTE_HASH=$(git hash-object origin/main:CHANGELOG.md 2>/dev/null || echo "")

if [ "$LOCAL_HASH" = "$REMOTE_HASH" ]; then
    echo "CHANGELOG.md 已是最新，无需更新。"
    exit 0
fi

git checkout origin/main -- CHANGELOG.md
echo "CHANGELOG.md 已同步到远程 main 最新版本。"
