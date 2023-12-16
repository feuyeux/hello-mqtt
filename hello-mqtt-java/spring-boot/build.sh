#!/bin/bash
SHELL_FOLDER=$(cd "$(dirname "$0")" || exit;pwd)
cd "$SHELL_FOLDER" || exit
export JAVA_HOME=/usr/local/opt/openjdk/libexec/openjdk.jdk/Contents/Home
mvn clean install -DskipTests
