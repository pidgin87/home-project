FROM openjdk:21-bullseye

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /docker/elastic-apm-agent-1.47.1.jar /application/elastic-apm-agent.jar
COPY /build/libs/room-service-0.0.1-SNAPSHOT.jar /application/runner.jar

WORKDIR /application

ENTRYPOINT ["./entrypoint.sh"]