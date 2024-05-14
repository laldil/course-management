#!/bin/bash

services=("authentication-service" "course-service" "discovery-server" "file-service" "gateway-service" "quiz-service")

for service in "${services[@]}"
do
    echo "Starting service: $service"
    cd "$service"
    mvn clean package
    nohup java -jar target/"$service"-0.0.1-SNAPSHOT.jar &
    cd ..
done

echo "All services started"
