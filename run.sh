#!/bin/bash

services=("authentication-service" "course-service" "discovery-server" "file-service" "gateway-service" "score-service")

for service in "${services[@]}"
do
    (
        echo "$(date '+%Y-%m-%d %H:%M:%S') - Starting service: $service"
        cd "$service"
        mvn clean package
        nohup java -jar target/"$service"-0.0.1-SNAPSHOT.jar > "../${service}.log" 2>&1 &
        cd ..
    ) &
done

wait
echo "$(date '+%Y-%m-%d %H:%M:%S') - All services started"