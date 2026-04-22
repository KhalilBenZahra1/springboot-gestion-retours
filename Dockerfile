FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/gestionretours-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]