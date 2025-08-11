# Use official Java image
FROM eclipse-temurin:24-jdk

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper & project files
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot application
CMD ["java", "-jar", "target/s4-sprint1-api-1.0.0.jar"]

# Expose app port
EXPOSE 8080

