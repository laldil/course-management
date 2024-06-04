#!/bin/bash
set -e

echo "Deployment started ..."

./stop.sh

# Check for uncommitted changes and stash them
if [ -n "$(git status --porcelain)" ]; then
    git stash --include-untracked
    STASHED=true
else
    STASHED=false
fi

# Pull the latest version of the app
git pull origin main --no-edit

# Apply stashed changes, if any
if [ "$STASHED" = true ]; then
    git stash apply || true
    git stash drop || true
fi

./run.sh

echo "Deployment finished!"