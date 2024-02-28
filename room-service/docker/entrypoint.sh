#!/bin/bash

java -javaagent:/application/elastic-apm-agent.jar \
  -Delastic.apm.service_name=room-service \
  -Delastic.apm.server_urls=http://10.5.0.13:8200 \
  -Delastic.apm.secret_token= \
  -Delastic.apm.application_packages=com.smirnoff \
  -Dlogging.config=/application/config/logback-spring.xml \
  -Xmx256M -Xms256M \
  -jar /application/runner.jar \
  --spring.config.location=file:///application/config/application.yaml
