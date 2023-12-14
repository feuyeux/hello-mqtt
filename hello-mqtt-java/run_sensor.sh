#!/bin/bash
SHELL_FOLDER=$(cd "$(dirname "$0")" || exit;pwd)
cd "$SHELL_FOLDER" || exit
pwd
cd mqtt-sensor
mvn spring-boot:run