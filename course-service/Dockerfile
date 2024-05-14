FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
WORKDIR /app/course-service
COPY . /app/course-service
RUN mvn clean package

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=MAVEN_BUILD /app/course-service/target/course-service-0.0.1-SNAPSHOT.jar /app/course-service.jar

CMD ["java", "-jar", "course-service.jar"]
