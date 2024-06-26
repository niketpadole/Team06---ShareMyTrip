# Use the official Maven image to build the application
# with a JDK 17 image
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn package -DskipTests

# Use the official OpenJDK 17 image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built jar file
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
# # Use the official OpenJDK image as a parent image
# FROM openjdk:17-jdk-slim

# # Set the working directory inside the container
# WORKDIR /app

# # Copy the jar file into the container
# COPY target/eurekaserver.jar eurekaserver.jar

# # Expose the port your application runs on
# EXPOSE 8761

# # Run the jar file
# ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]
