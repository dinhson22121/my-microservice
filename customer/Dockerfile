FROM maven:3.8-adoptopenjdk-16 AS build

# Set the working directory
WORKDIR /app

# Copy the parent pom.xml file to the container
COPY pom.xml .

COPY customer ./customer


# Download the parent project dependencies
RUN mvn dependency:go-offline -B

# Build the Customer submodule
RUN cd customer && mvn package spring-boot:repackage -DskipTests && cd ..

# Set the base image for the final stage
FROM adoptopenjdk:16 AS runtime

# Set the working directory
WORKDIR /app

# Copy the built JAR files from the submodules
COPY --from=build /app/customer/target/customer-1.0-SNAPSHOT.jar customer.jar

# Set the command to run each submodule
CMD ["java", "-jar", "customer.jar"]

# Expose necessary ports for each submodule
# Modify these commands according to the ports required for each submodule
EXPOSE 8089
