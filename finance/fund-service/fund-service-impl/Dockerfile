FROM openjdk:21-bullseye

EXPOSE 10004
WORKDIR /application

COPY /fund-service-impl/docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/fund-service-0.0.1-SNAPSHOT.jar /application/runner.jar

ENTRYPOINT ["./entrypoint.sh"]