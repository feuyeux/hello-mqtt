#!/bin/bash
SHELL_FOLDER=$(cd "$(dirname "$0")" || exit;pwd)
cd "$SHELL_FOLDER" || exit

mosquitto_pw_path=/tmp/mosquitto

if [ ! -d $mosquitto_pw_path ]
then
     mkdir $mosquitto_pw_path
else
     echo ""
fi

#mosquitto_passwd -c conf/mosquitto_pwfile.example admin
cp conf/mosquitto_pwfile.example /tmp/mosquitto/pwfile.example
mosquitto -c $(pwd)/conf/mosquitto.conf