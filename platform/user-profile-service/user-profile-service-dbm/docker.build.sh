#!/bin/bash

docker buildx build \
  --platform linux/amd64 \
  -t smirnoke/home-project-user-profile-dbm \
  --push .