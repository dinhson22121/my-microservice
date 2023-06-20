# Set the base image
FROM maven:3.8-adoptopenjdk-16 AS build

# Set the working directory
WORKDIR /app

# Copy the parent pom.xml file to the container
COPY pom.xml .

# Copy the submodules' source code and build each submodule
COPY eureka-server ./eureka-server
COPY fraud ./fraud
COPY customer ./customer


# Download the parent project dependencies
RUN mvn dependency:go-offline -B

# Build the Eureka submodule
RUN cd eureka-server && mvn package spring-boot:repackage -DskipTests && cd ..

# Build the Fraud submodule
RUN cd fraud && mvn package spring-boot:repackage -DskipTests && cd ..

# Build the Customer submodule
RUN cd customer && mvn package spring-boot:repackage -DskipTests && cd ..

# Set the base image for the final stage
FROM adoptopenjdk:16 AS runtime

# Set the working directory
WORKDIR /app

# Copy the built JAR files from the submodules
COPY --from=build /app/eureka-server/target/eureka-server-1.0-SNAPSHOT.jar eureka-server.jar
#ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
#EXPOSE 8761
COPY --from=build /app/fraud/target/fraud-1.0-SNAPSHOT.jar fraud.jar
#CMD java -jar fraud.jar
#EXPOSE 8090
COPY --from=build /app/customer/target/customer-1.0-SNAPSHOT.jar customer.jar
#CMD java -jar customer.jar
#EXPOSE 8089

# Set the command to run each submodule
#CMD ["java", "-jar", "eureka-server.jar","&","java", "-jar", "fraud.jar","&","java", "-jar", "customer.jar"]
CMD java -jar eureka-server.jar -o -p 8761 & java -jar fraud.jar -o -p 8090 & java -jar customer.jar -o -p 8089
# Expose necessary ports for each submodule
# Modify these commands according to the ports required for each submodule& java -jar fraud.jar -o -p 8090 & java -jar customer.jar -o -p 8089
