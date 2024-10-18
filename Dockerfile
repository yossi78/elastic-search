# Use a Java base image
 FROM openjdk:17-jdk-slim

# Set the working directory inside the container
 WORKDIR /app

# Copy the jar file from target directory to the container
 COPY target/elastic-search-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
 EXPOSE 8080

# Run the jar file
 ENTRYPOINT ["java", "-jar", "app.jar"]
