# Use an official OpenJDK runtime as a parent image
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/spring-boot-chatgpt.jar /app/spring-boot-chatgpt.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "spring-boot-chatgpt.jar"]
