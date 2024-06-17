#!/bin/bash

java -Duser.timezone="Pacific/Auckland" \
     -Dspring.profiles.active=production \
     -jar /application/runner.jar
