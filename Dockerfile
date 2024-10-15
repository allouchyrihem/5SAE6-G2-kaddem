# Use a base Java image
FROM openjdk:17-jdk-alpine

# Create a directory for the application
WORKDIR /app

# Copy the generated JAR file into the container
COPY target/kaddem.jar app.jar

# Expose the port on which the application will be available
EXPOSE 8089

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
