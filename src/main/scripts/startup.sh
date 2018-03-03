#!/bin/sh
#RROGRAM
# EXECUTE service.sh start


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

	echo "startup.sh based at $BASE_DIR"
fi


$BASE_DIR/bin/service.sh start
