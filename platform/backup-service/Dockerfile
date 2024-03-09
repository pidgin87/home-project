FROM openjdk:21-bullseye

EXPOSE 5500

COPY /docker/entrypoint.sh /application/entrypoint.sh

RUN chmod +x /application/entrypoint.sh

WORKDIR /application

ENTRYPOINT ["./entrypoint.sh"]

COPY /build/libs/backup-service-0.0.1-SNAPSHOT.jar /application/runner.jar
