#!/bin/bash

EXTRA_OPTS=""

if [ $DEBUG ]; then
  EXTRA_OPTS="$EXTRA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
fi

java $EXTRA_OPTS \
     -Dspring.profiles.active=production \
     -jar /application/runner.jar
