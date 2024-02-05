FROM openjdk:21-bullseye

COPY /docker/entrypoint.sh /application/entrypoint.sh
COPY /build/libs/job-service-0.0.1-SNAPSHOT.jar /application/runner.jar

RUN chmod +x /application/entrypoint.sh

WORKDIR /application

ENTRYPOINT ["./entrypoint.sh"]