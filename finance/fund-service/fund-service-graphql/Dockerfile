FROM openjdk:21-bullseye

EXPOSE 10004
WORKDIR /application

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/runner.jar /application/runner.jar

ENTRYPOINT ["./entrypoint.sh"]