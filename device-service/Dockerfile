FROM openjdk:21-bullseye

EXPOSE 10001

COPY /docker/entrypoint.sh /application/entrypoint.sh
COPY /docker/bootstrap.yml /application/config/bootstrap.yml
COPY /build/libs/device-service-0.0.1-SNAPSHOT.jar /application/runner.jar

RUN chmod +x /application/entrypoint.sh

WORKDIR /application

ENTRYPOINT ["./entrypoint.sh"]