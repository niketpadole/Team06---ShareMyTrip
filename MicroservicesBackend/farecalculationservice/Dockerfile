# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built jar file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8094

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
