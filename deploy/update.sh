#!/usr/bin/env bash

sudo /etc/init.d/scheduleserver stop
cd /opt/scheduleserver
# down load new version from artifactory using wget
rm scheduleserver.jar
# rename new jar file to scheduleserver.jar
sudo /etc/init.d/scheduleserver start


