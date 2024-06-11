FROM openjdk:22-bullseye

WORKDIR /application

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/user-profile-service-dbm-0.0.1-SNAPSHOT.jar /application/dbm.jar

ENTRYPOINT ["./entrypoint.sh"]