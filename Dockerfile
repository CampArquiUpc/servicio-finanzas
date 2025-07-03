FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/servicio-finanzas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

LABEL authors="brayan"

ENTRYPOINT ["java", "-jar", "app.jar"]