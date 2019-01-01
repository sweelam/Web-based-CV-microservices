FROM java:openjdk-8-alpine

COPY ./target/eureka-service-0.0.1-SNAPSHOT.jar ./

EXPOSE 8761

CMD ["java", "-jar", "eureka-service-0.0.1-SNAPSHOT.jar"]