# Use a base Java image
FROM openjdk:17-jdk-alpine

# Create a directory for the application
WORKDIR /kaddemApp

# Copy the generated JAR file into the container
COPY target/kaddem-0.0.1.jar kaddem.jar

# Expose the port on which the application will be available
EXPOSE 8090

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "kaddem.jar"]