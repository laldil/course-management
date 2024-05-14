FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
WORKDIR /app/discovery-server
COPY . /app/discovery-server
RUN mvn clean package

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=MAVEN_BUILD /app/discovery-server/target/discovery-server-0.0.1-SNAPSHOT.jar /app/discovery-server.jar

CMD ["java", "-jar", "discovery-server.jar"]
