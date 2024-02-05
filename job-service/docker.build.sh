#!/bin/bash

docker buildx build \
  --platform linux/amd64 \
  -t smirnoke/home-garden:job-service \
  --push .