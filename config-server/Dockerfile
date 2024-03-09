FROM openjdk:21-bullseye

EXPOSE 7000

COPY /docker/entrypoint.sh /application/entrypoint.sh
COPY /build/libs/config-server-0.0.1-SNAPSHOT.jar /application/config-server-runner.jar

RUN chmod +x /application/entrypoint.sh
WORKDIR /application

ENTRYPOINT ["./entrypoint.sh"]