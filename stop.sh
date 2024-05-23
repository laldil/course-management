#!/bin/bash

services=("authentication-service" "course-service" "discovery-server" "file-service" "gateway-service" "quiz-service")

for service in "${services[@]}"
do
    echo "Stopping service: $service"
    pkill -f "$service"-0.0.1-SNAPSHOT.jar
done

echo "All services stopped"
