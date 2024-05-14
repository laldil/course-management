FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
WORKDIR /app/quiz-service
COPY . /app/quiz-service
RUN mvn clean package

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=MAVEN_BUILD /app/quiz-service/target/quiz-service-0.0.1-SNAPSHOT.jar /app/quiz-service.jar

CMD ["java", "-jar", "quiz-service.jar"]
