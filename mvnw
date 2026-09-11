#!/bin/sh
# Maven Wrapper Script
# Runs Maven using the wrapper version declared in .mvn/wrapper/maven-wrapper.properties
exec "$(dirname "$0")/.mvn/wrapper/mvn" "$@"
