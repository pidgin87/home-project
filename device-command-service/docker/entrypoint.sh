#!/bin/bash

java \
  -Xmx256M \
  -Xms256M \
  --add-opens java.base/java.lang.reflect=ALL-UNNAMED \
  --add-opens java.base/java.lang=ALL-UNNAMED \
  -Dspring.profiles.active=production \
  -jar /application/runner.jar


