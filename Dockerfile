# Stage 1: Construction
FROM maven:3.8.6-eclipse-temurin-17 AS builder

# Create a work directory
WORKDIR /app

# Copy files from project to container
COPY pom.xml .
COPY src ./src

# Build project
RUN mvn clean package -DskipTests

# Stage 2: Execution
FROM eclipse-temurin:17-jdk-alpine

# Create a work directory
WORKDIR /app

# Copy the generated JAR file from the construction stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port used by the app
EXPOSE 8080

# Command to execute the app
ENTRYPOINT ["java", "-jar", "app.jar"]