FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
WORKDIR /app/gateway-service
COPY . /app/gateway-service
RUN mvn clean package

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=MAVEN_BUILD /app/gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar /app/gateway-service.jar

CMD ["java", "-jar", "gateway-service.jar"]
