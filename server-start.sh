#!/bin/bash

echo 'Start frontend'
npm start --prefix ang-in-nutshel/ &    

echo 'build eureka server'
mvn clean install -f ./eureka/pom.xml -DskipTests

echo 'build config server'
mvn clean install -f ./configServer/pom.xml -DskipTests

echo 'build admin server'
mvn clean install -f ./admin-server/pom.xml  -DskipTests

echo 'build proxy server'
mvn clean install -f ./web-cv-proxy/pom.xml -DskipTests

echo 'build cv-stack'
mvn clean install -f ./web-based-cv/pom.xml  -DskipTests

echo 'build stack using compose'
docker-compose build

echo 'Start stack'
docker-compose --compatibility up

