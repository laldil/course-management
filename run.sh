#!/bin/bash

mkdir -p logs

services=("authentication-service" "course-service" "discovery-server" "file-service" "gateway-service" "score-service" "notification-service")

for service in "${services[@]}"
do
    echo "Starting service: $service"
    cd "$service"
    mvn clean package
    nohup java -jar target/"$service"-0.0.1-SNAPSHOT.jar > "../logs/$service.log" 2>&1 &
    if [ $? -eq 0 ]; then
        echo "$service started successfully"
    else
        echo "Failed to start $service"
        exit 1
    fi
    cd ..
done

echo "All services started"
