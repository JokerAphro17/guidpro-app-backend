
# Builder stage
FROM amazoncorretto:17.0.7-alpine as builder
LABEL authors="THE JOKER"
# Set the working directory in the Docker container
WORKDIR /workspace/app

# Copy the Gradle executable
COPY gradlew .
COPY gradle gradle

# Copy the Gradle configuration files
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

# Copy the project source
COPY src src

# Grant execution rights on the Gradle wrapper
RUN chmod +x ./gradlew

# Build the application (without running tests)
RUN ./gradlew build -x test

# Runner stage
FROM amazoncorretto:17.0.7-alpine

# Set the working directory in the Docker container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /workspace/app/build/libs/*.jar app.jar

# Command to run the application add keystores to the container
ENTRYPOINT ["java", "-jar", "app.jar"]
