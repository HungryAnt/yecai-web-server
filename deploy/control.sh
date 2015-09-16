#!/bin/bash
[ $# -lt 1 ] && exit -1

PRO_HOME=$(dirname $(readlink -f $0))/..
PRO_NAME=yecai-service
SUPERVISE_MODULE_NAME="supervise.${PRO_NAME}"
SUPERVISE_MODULE="${PRO_HOME}/bin/${SUPERVISE_MODULE_NAME}"
PRO_MODULE_NAME=yecai-service-1.0-SNAPSHOT.jar
PRO_MODULE="${PRO_HOME}/bin/${PRO_MODULE_NAME}"

MAX_MEMORY=2048M
MAX_PERM_MEMORY=512M

APP_PROPERTIES="${PRO_HOME}/conf/application.properties"
ENDPOTIN_CONFIG_FILE=file:"${PRO_HOME}/conf/endpoint.json"

HOST_NAME=`hostname`

start() {
    stop

    echo "PRO_HOME=$PRO_HOME"
    echo "SUPERVISE_MODULE=$SUPERVISE_MODULE"
    echo "APP_PROPERTIES=$APP_PROPERTIES"
    echo "HOST_NAME=$HOST_NAME"

    [ -e $APP_PROPERTIES ] || (echo "$APP_PROPERTIES does not exist" && exit -1)

    STATUS_DIR="${PRO_HOME}/status"
    [ -d $STATUS_DIR ] || mkdir $STATUS_DIR

    chmod +x $SUPERVISE_MODULE

    HOST_NAME_ARG=-Dserver-host=${HOST_NAME}

    SUPERVISE_CMD="java -Dspring.config.location=$APP_PROPERTIES -Dendpoint.config=$ENDPOTIN_CONFIG_FILE \
$HOST_NAME_ARG -Dfile.encoding=UTF-8 -Xmx$MAX_MEMORY \
-XX:MaxPermSize=$MAX_PERM_MEMORY -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps \
-Xloggc:gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=20M \
-jar $PRO_MODULE "

    echo "SUPERVISE_CMD=$SUPERVISE_CMD"
    $SUPERVISE_MODULE -p ${STATUS_DIR}/${PRO_NAME} -f "$SUPERVISE_CMD" >/dev/null 2>&1 &
}

stop() {
    killall ${SUPERVISE_MODULE_NAME}
    ps aux | grep $PRO_MODULE_NAME | grep -v grep | awk '{print $2}' | xargs kill -9
}

cmdName="control-$1.sh"

case C"$1" in
    C)
    echo "Usage: $cmdName {start|stop}"
    ;;
    Cstart)
    start
    echo "done!"
    ;;
    Cstop)
    stop
    echo "done!"
    ;;
    Crestart)
    start
    echo "done!"
    ;;
    C*)
    echo "Usage: $cmdName {start|stop}"
    ;;
esac
