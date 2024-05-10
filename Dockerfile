# the base image
FROM amazoncorretto:17

# the JAR file path
ARG JAR_FILE=./backend/target/*.jar

# Copy the JAR file from the build context into the Docker image
COPY ${JAR_FILE} application.jar

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java", "-jar", "/application.jar"]
