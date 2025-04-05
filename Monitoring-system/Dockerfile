FROM openjdk:22-jdk

WORKDIR /app

COPY target/Monitoring-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 2563

CMD ["java", "-jar", "app.jar"]