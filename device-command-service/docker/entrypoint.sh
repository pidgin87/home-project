#!/bin/bash

java -Dlogging.config=/application/config/logback-spring.xml \
  -Xmx256M -Xms256M \
  -jar /application/runner.jar \
  --spring.config.location=file:///application/config/application.yaml
