#!/bin/bash
 

if [ -z "$JAVA_HOME" ]; then
  export JAVA=`which java`
else
  export JAVA="$JAVA_HOME/bin/java"
fi

SERVER_NAME="smsa-pay"
STARTUP_CLASS="com.ucpaas.sms.Application"

export JMX_PORT=34888
export CLASSPATH=$BASE_DIR/conf:$(ls $BASE_DIR/lib/*.jar | tr '\n' :)

JVM_APGS="-Xmx4048m -Xms256m -XX:PermSize=128m -server -cp $CLASSPATH "
 
if [ -z "$JVM_APGS" ]; then
	export CUSTOME_JVM_APGS=$JVM_APGS
fi
