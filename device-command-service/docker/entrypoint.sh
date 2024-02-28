#!/bin/bash

java -Dlogging.config=/application/config/logback-spring.xml \
  -Xmx256M \
  -Xms256M \
  --add-opens java.base/java.lang.reflect=ALL-UNNAMED \
  --add-opens java.base/java.lang=ALL-UNNAMED \
  -jar /application/runner.jar \
  --spring.config.location=file:///application/config/application.yaml

