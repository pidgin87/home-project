#!/bin/bash

### Parameters
PROJECT_NAME=home-project-finance-history-graphql

### External parameters
source gradle.properties

### Logic
cp ./build/libs/history-service-graphql-$version.jar ./build/libs/$PROJECT_NAME.jar

docker buildx build \
  --platform linux/amd64 \
  -t smirnoke/$PROJECT_NAME:$version \
  --push .