# Etapa 1: Usamos una imagen con Maven para compilar tu código
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Usamos una imagen más ligera solo con Java para correr tu app
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto que usa Spring Boot
EXPOSE 8080

# Comando para encender tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]