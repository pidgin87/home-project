#!/bin/bash

java -Xmx256M -Xms256M \
     -Dspring.profiles.active=production \
     -jar /application/dbm.jar online-migrate
