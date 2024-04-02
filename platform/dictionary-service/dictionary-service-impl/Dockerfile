FROM openjdk:21-bullseye

EXPOSE 10007
WORKDIR /application

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/dictionary-service-impl-0.0.1-SNAPSHOT.jar /application/runner.jar

ENTRYPOINT ["./entrypoint.sh"]