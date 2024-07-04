FROM openjdk:22-bullseye

EXPOSE 11100
EXPOSE 5005

ENV DEBUG=false

WORKDIR /application

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/ui.jar /application/runner.jar

ENTRYPOINT ["./entrypoint.sh"]