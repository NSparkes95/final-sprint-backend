# Use OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Set work directory
WORKDIR /app

# Copy Maven build files
COPY target/dsa-bst-1.0-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
