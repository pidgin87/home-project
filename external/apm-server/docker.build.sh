#!/bin/bash

docker buildx build \
  --platform linux/amd64 \
  -t smirnoke/home-garden:apm-server \
  --push .