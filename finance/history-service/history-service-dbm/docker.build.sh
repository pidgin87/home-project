#!/bin/bash

### Parameters
PROJECT_NAME=home-project-finance-history-dbm

### External parameters
source gradle.properties

### Logic
cp ./build/libs/history-service-dbm-$version.jar ./build/libs/$PROJECT_NAME.jar

docker buildx build \
  --platform linux/amd64 \
  -t smirnoke/$PROJECT_NAME:$version \
  --push .