#!/usr/bin/env bash
sudo mkdir /opt/scheduleserver
sudo mkdir /opt/scheduleserver/logs

sudo nano /opt/scheduleserver/scheduleserver_deploy.sh
# copy/paste scheduleserver_deploy.sh file
sudo chmod 755 /opt/scheduleserver/scheduleserver_deploy.sh

sudo nano /etc/sudoers
#add: jenkins ALL=NOPASSWD:/opt/scheduleserver/scheduleserver_deploy.sh


# running service with system-v for U14.04
    sudo nano /etc/init.d/scheduleserver
    # copy/paste scheduleserver file
    sudo chmod 755 /etc/init.d/scheduleserver
    # configure service start/stop with chkconfig/
    sudo sysv-rc-conf --level 234 scheduleserver on
    sudo sysv-rc-conf --level 056 scheduleserver off

# running service with systemd for U16.04
    sudo nano /opt/scheduleserver/scheduleserver
    # copy/paste scheduleserver file
    sudo chmod 755 /opt/scheduleserver/scheduleserver
    sudo nano /lib/systemd/system/scheduleserver.service
    # copy/paste scheduleserver.service file
    sudo systemctl enable scheduleserver.service
    sudo systemctl daemon-reload && sudo service scheduleserver restart


# refer steps in update.sh

