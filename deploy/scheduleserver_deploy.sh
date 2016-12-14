#!/bin/bash
VERSION=$1
WORKING_FOLDER=/opt/scheduleserver

count=0
while true; do
	wget -O $WORKING_FOLDER/stage-$1.jar http://CI:8081/artifactory/libs-release-local/com/lhmtech/commerce/scheduleserver/$VERSION/scheduleserver-$VERSION.jar
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
	mv $WORKING_FOLDER/stage-$1.jar $WORKING_FOLDER/scheduleserver.jar
	service scheduleserver restart
fi
exit $RETVAL
