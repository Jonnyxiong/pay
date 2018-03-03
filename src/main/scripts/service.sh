#!/bin/bash
if [ -z "$BASE_DIR" ] ; then
	PRG="$0"
	# need this for relative symlinks
	while [ -h "$PRG" ] ; do
		ls=`ls -ld "$PRG"`
		link=`expr "$ls" : '.*->\(.*\)$'`

		if expr "$link" : '/.*' > /dev/null; then
			  PRG="$link"
   			else
      		  PRG="`dirname "$PRG"`/$link"
		fi
	done

	BASE_DIR=`dirname "$PRG"`/..

	BASE_DIR=`cd "$BASE_DIR" && pwd`

	echo "service.sh based at $BASE_DIR"
fi

source $BASE_DIR/bin/env.sh

AS_USER=`whoami`
LOG_DIR="$BASE_DIR/logs"
LOG_FILE="$LOG_DIR/server.log"
PID_DIR="$BASE_DIR/logs"
PID_FILE="$PID_DIR/.run.pid"


function running(){
    if [ -f "$PID_FILE" ]; then
        pid=$(cat "$PID_FILE")
        process=`ps aux | grep " $pid " | grep -v grep`;
        if [ "$process" == "" ]; then
            return 1;
        else
            return 0;
        fi
    else
        return 1
    fi
}

function start_server(){
	if running; then
		echo "$SERVER_NAME is running!"
		exit 1
	fi

	mkdir -p $PID_DIR
	mkdir -p $LOG_DIR
	chown -R $AS_USER $PID_DIR
	chown -R $AS_USER $LOG_DIR
	touch $LOG_FILE

	echo "$JAVA $CUSTOME_JVM_APGS  -DappName=smsa-pay $STARTUP_CLASS"
	sleep 1
	nohup $JAVA $CUSTOME_JVM_APGS  -DappName=smsa-pay $SERVER_NAME 2>&1 >>$LOG_FILE &

	echo $! > $PID_FILE
	chmod 755 $PID_FILE
	sed -i "s/system.status=running//g" $BASE_DIR/conf/system.properties
	sed -i "s/system.status = running//g" $BASE_DIR/conf/system.properties
	sed -i "s/system.status = stop//g" $BASE_DIR/conf/system.properties
	sed -i "s/system.status=stop//g" $BASE_DIR/conf/system.properties
    echo -e "\nsystem.status = running" >> $BASE_DIR/conf/system.properties
	# sleep 1;
	# tail -F $LOG_FILE

}

function stop_server(){
	if ! running; then
		echo "$SERVER_NAME is not running!"
		exit 1
	fi
	count=0
	eleganceCount=0
	pid=$(cat $PID_FILE)
	while running; do
	    let eleganceCount=$eleganceCount+1
    	echo "Stopping elegance $SERVER_NAME $eleganceCount times"
	    sed -i "s/system.status = running/system.status=stop/g" $BASE_DIR/conf/system.properties

        if [ $eleganceCount -eq 20 ]
       then
          break
       fi
        sleep 3;

	done

    if ! running; then
        echo "Stop $SERVER_NAME successfully."
        rm $PID_FILE
        return 0
    fi

	while running; do
    	let count=$count+1
    	echo "Stopping $SERVER_NAME $count times"
    	# echo kill -9 $pid
        kill -9 $pid
        sleep 3;
	done

	echo "Stop $SERVER_NAME successfully."
	rm $PID_FILE
}

function status(){
	if running; then
		echo "$SERVER_NAME is running."
	else
		echo "$SERVER_NAME is not running."
	fi
}

command=$1
shift 1

case $command in
    start)
        start_server $@;
        ;;
    stop)
        stop_server $@;
        ;;
    status)
        status $@;
        ;;
    restart)
        $0 stop $@
        $0 start $@
        ;;
    help)
        help;
        ;;
    *)
        help;
        exit 1;
        ;;
esac
