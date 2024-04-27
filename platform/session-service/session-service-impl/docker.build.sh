#!/bin/bash

docker buildx build \
  --platform linux/amd64 \
  -t smirnoke/home-project-platform-user-profile-service \
  --push .