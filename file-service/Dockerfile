FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
WORKDIR /app/file-service
COPY . /app/file-service
RUN mvn clean package

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=MAVEN_BUILD /app/file-service/target/discovery-server-0.0.1-SNAPSHOT.jar /app/file-service.jar

CMD ["java", "-jar", "file-service.jar"]
