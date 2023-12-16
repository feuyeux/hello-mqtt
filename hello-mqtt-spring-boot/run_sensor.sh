#!/bin/bash
SHELL_FOLDER=$(cd "$(dirname "$0")" || exit;pwd)
cd "$SHELL_FOLDER" || exit
pwd
cd mqtt-sensor
export JAVA_HOME=/usr/local/opt/openjdk/libexec/openjdk.jdk/Contents/Home
mvn spring-boot:run