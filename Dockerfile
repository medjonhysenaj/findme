# Use Eclipse Temurin Java 17 JDK as base image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Make Maven Wrapper executable and build the app (skip tests for speed)
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Run the generated jar file
CMD ["sh", "-c", "java -jar target/*.jar"]
