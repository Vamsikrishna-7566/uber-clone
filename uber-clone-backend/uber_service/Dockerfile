# Use Java 23 image instead of 17
FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY target/uber_clone-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
