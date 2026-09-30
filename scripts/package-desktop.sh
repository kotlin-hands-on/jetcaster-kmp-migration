#!/usr/bin/env bash
# Native desktop installers for Jetcaster without Gradle: `kotlin package` makes an executable jar,
# jpackage turns it into a DMG (macOS), MSI (Windows, needs WiX) or DEB (Linux).
# Mirrors the Compose Desktop `nativeDistributions` block the Gradle build had.
#
#   package-desktop.sh [dmg|msi|deb]       # default: the format native to this OS
#   JPACKAGE=/path/to/jdk/bin/jpackage package-desktop.sh dmg
#
# Output: desktopApp/build-dist/<format>/Jetcaster-1.0.0.<format>
# Run from the project root.
set -euo pipefail

case "$(uname -s)" in
    Darwin) native=dmg ;; Linux) native=deb ;; *) native=msi ;;
esac
FORMAT="${1:-$native}"
NAME=Jetcaster
VERSION=1.0.0
PACKAGE=com.example.jetcaster

# jpackage: $JPACKAGE, else $JAVA_HOME, else the newest JDK the Kotlin Toolchain provisioned.
if [ -z "${JPACKAGE:-}" ]; then
    if [ -n "${JAVA_HOME:-}" ] && [ -x "$JAVA_HOME/bin/jpackage" ]; then
        JPACKAGE="$JAVA_HOME/bin/jpackage"
    else
        cache="${KOTLIN_SHARED_CACHE_DIR:-$HOME/Library/Caches/JetBrains/Kotlin}/extract.cache"
        JPACKAGE="$(find "$cache" -path '*bin/jpackage' -type f 2>/dev/null | sort -V | tail -1)"
    fi
fi
[ -x "${JPACKAGE:-}" ] || { echo "package-desktop: no jpackage found; set JPACKAGE or JAVA_HOME" >&2; exit 1; }
echo "jpackage: $JPACKAGE"

# Not `kotlin package -f executable-jar`: as of 0.13.0-dev-4452 it flattens dependencies into
# BOOT-INF/lib by file name, so e.g. the empty org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-desktop
# redirect stub overwrites androidx.lifecycle:lifecycle-viewmodel-desktop and the app dies with
# NoClassDefFoundError: androidx/lifecycle/ViewModel. Instead, take the exact runtime classpath that
# `kotlin run` uses: -XshowSettings prints it and -version makes the JVM exit before main() runs.
INPUT="$(mktemp -d)"
trap 'rm -rf "$INPUT"' EXIT
./kotlin run -m desktopApp --no-compose-hot-reload --jvm-args="-XshowSettings:properties -version" 2>&1 \
    | awk '/java.class.path = /{f=1} f && /^ +[a-z]+\.[a-z.]+ = / && !/java.class.path/{f=0} f' \
    | sed 's/^ *java.class.path = //; s/^ *//' > "$INPUT/classpath.txt"
n=0
while IFS= read -r jar; do
    [ -f "$jar" ] || continue
    n=$((n + 1))
    # Prefix with the position so same-named jars from different groups cannot collide.
    cp "$jar" "$INPUT/$(printf '%03d' "$n")-$(basename "$jar")"
done < "$INPUT/classpath.txt"
rm "$INPUT/classpath.txt"
(( n > 0 )) || { echo "package-desktop: could not read the runtime classpath" >&2; exit 1; }
MAIN_JAR="$(cd "$INPUT" && ls ./*-desktopApp-jvm.jar | head -1)"
echo "packaging $n jars, main jar $MAIN_JAR"
# appResourcesRootDir: files in desktopApp/assets/common ship next to the jars (the splash image).
cp -R desktopApp/assets/common/. "$INPUT/"

OUT="desktopApp/build-dist/$FORMAT"
rm -rf "$OUT" && mkdir -p "$OUT"
"$JPACKAGE" --type "$FORMAT" --name "$NAME" --app-version "$VERSION" \
    --input "$INPUT" --main-jar "${MAIN_JAR#./}" --main-class com.example.jetcaster.shared.MainKt \
    --java-options '-splash:$APPDIR/logo.gif' \
    $([ "$FORMAT" = dmg ] && echo --mac-package-identifier "$PACKAGE") \
    --dest "$OUT"
ls -la "$OUT"
