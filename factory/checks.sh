#!/usr/bin/env bash
# The repo's checks. Exit 0 only when the branch is fit to review.
#
# The gate runs this script from the main checkout with the job's worktree as
# the working directory. Keep it fast, deterministic and non-interactive: no
# watch modes, no prompts, nothing that rewrites files.
set -euo pipefail

# Gradle 8.5 (this project's wrapper) doesn't support running on very new JDKs.
# Pin to a JDK matching build.gradle.kts's sourceCompatibility (17) if one is
# installed, instead of whatever the system default happens to be.
if command -v /usr/libexec/java_home >/dev/null 2>&1; then
  if JAVA17_HOME=$(/usr/libexec/java_home -v 17 2>/dev/null); then
    export JAVA_HOME="$JAVA17_HOME"
  fi
fi

./gradlew --no-daemon --console=plain build
