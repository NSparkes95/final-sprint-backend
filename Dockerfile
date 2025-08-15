# Stage 1 — Build the JAR

FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
# Copy pom.xml first (better layer caching)
COPY pom.xml ./
RUN mvn dependency:go-offline
# Copy source code
COPY src ./src
# Build the Spring Boot fat JAR (skip tests for speed)
RUN mvn clean package -DskipTests

# Stage 2 — Run the JAR

FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copy only the built JAR from the build stage
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
