FROM openjdk:22-bullseye

EXPOSE 10006
WORKDIR /application

COPY /docker/entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

COPY /build/libs/home-project-finance-history-graphql.jar /application/runner.jar

ENTRYPOINT ["./entrypoint.sh"]