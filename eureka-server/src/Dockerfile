# Stage 1: Build ứng dụng với Maven
FROM maven:3.8-adoptopenjdk-16 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Tạo image triển khai
FROM adoptopenjdk:16 AS runtime
WORKDIR /app
COPY --from=build /app/target/eureka-server-1.0-SNAPSHOT.jar /app/eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar"]
