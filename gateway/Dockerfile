FROM openjdk:21-bullseye

EXPOSE 8000

COPY /docker/entrypoint.sh /application/entrypoint.sh
WORKDIR /application

COPY /build/libs/gateway-0.0.1-SNAPSHOT.jar /application/runner.jar

RUN chmod +x /application/entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]