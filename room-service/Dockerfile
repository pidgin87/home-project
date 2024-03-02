FROM openjdk:21-bullseye

EXPOSE 10000

COPY /docker/entrypoint.sh /application/entrypoint.sh
COPY /docker/bootstrap.yml /application/config/bootstrap.yml
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/room-service-0.0.1-SNAPSHOT.jar /application/runner.jar

WORKDIR /application

ENTRYPOINT ["./entrypoint.sh"]