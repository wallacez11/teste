# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

COPY target/teste-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080


CMD ["java", "-jar", "teste-0.0.1-SNAPSHOT.jar"]