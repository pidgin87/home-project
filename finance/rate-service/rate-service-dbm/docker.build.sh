#!/bin/bash

### Parameters
PROJECT_NAME=home-project-finance-rate-dbm

### External parameters
source gradle.properties

### Logic
cp ./build/libs/rate-service-dbm-$version.jar ./build/libs/runner.jar

docker buildx build \
  --platform linux/amd64 \
  -t smirnoke/$PROJECT_NAME:$version \
  --push .