#@/bin/bash

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
/home/msweelam/Work/Programs/redis-6.0.8/src/redis-server &

echo 'build stack using compose'
docker-compose build

echo 'Start stack'
docker-compose up

