FROM openjdk:22-bullseye

EXPOSE 10005
WORKDIR /application

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/product-service-graphql-0.0.1-SNAPSHOT.jar /application/runner.jar

ENTRYPOINT ["./entrypoint.sh"]