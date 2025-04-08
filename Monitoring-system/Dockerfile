FROM openjdk:17-jdk-slim

WORKDIR /app
COPY target/*-release.jar app.jar
COPY src/main/webapp /app/src/main/webapp
EXPOSE 2563

ENTRYPOINT ["java","-jar","app.jar"]