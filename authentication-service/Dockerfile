FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
WORKDIR /app/authentication-service
COPY . /app/authentication-service
RUN mvn clean package

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=MAVEN_BUILD /app/authentication-service/target/authentication-service-0.0.1-SNAPSHOT.jar /app/authentication-service.jar

CMD ["java", "-jar", "authentication-service.jar"]
