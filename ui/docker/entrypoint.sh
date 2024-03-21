#!/bin/bash

java -Dspring.profiles.active=production \
     -jar /application/runner.jar
