#!/bin/sh
exec "$(dirname "$0")/gradle-8.1.1/bin/gradle" "$@" 2>/dev/null || exec gradle "$@"
