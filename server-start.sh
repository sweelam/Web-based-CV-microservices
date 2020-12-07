#!/bin/bash

echo 'build eureka server'
mvn clean install -f ./eureka/pom.xml

echo 'build config server'
mvn clean install -f ./configServer/pom.xml

echo 'build admin server'
mvn clean install -f ./admin-server/pom.xml 

echo 'build proxy server'
mvn clean install -f ./web-cv-proxy/pom.xml

echo 'build cv-stack'
mvn clean install -f ./web-based-cv/pom.xml 

echo 'Starting redis'
docker rm std-redis 
sleep 2s
docker run --name std-redis -d redis -p 6379:6379 &

echo 'build stack using compose'
docker-compose build

echo 'Start stack'
docker-compose --compatibility up

