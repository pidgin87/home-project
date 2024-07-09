FROM openjdk:22-bullseye

EXPOSE 8080

WORKDIR /application

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/runner.jar /application/procedure.jar

ENTRYPOINT ["./entrypoint.sh"]