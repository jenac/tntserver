#!/usr/bin/env bash
VERSION=$1
WORKING_FOLDER=/home/tntuser/tntserver

count=0
while true; do
	wget -O $WORKING_FOLDER/stage-$VERSION.jar https://github.com/tnt-develop-team/tntserver/releases/download/$VERSION/tntserver-$VERSION.jar
	RETVAL=$?
	if [[ $RETVAL -eq 0 ]];then
		break
	fi
	echo "retry $count / 60"
	count=$[$count + 1]
	sleep 1s
	if [ $count -gt 60 ];then
	    RETVAL=404
		break
	fi
done
if [[ $RETVAL -eq 0 ]];then
	echo 'download successful, replace file and restart service'
	mv $WORKING_FOLDER/stage-$VERSION.jar $WORKING_FOLDER/tntserver.jar
	chmod a+x $WORKING_FOLDER/tntserver.jar
	service tntserver restart
fi
exit $RETVAL
