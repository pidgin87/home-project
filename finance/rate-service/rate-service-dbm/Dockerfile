FROM openjdk:22-bullseye

WORKDIR /application

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/runner.jar /application/dbm.jar

ENTRYPOINT ["./entrypoint.sh"]