FROM java:openjdk-8-alpine
LABEL author="Mohamed Sweelam"
LABEL version="1.0"
LABEL description="Service discovery"
COPY ./target/eureka-service-0.0.1-SNAPSHOT.jar ./
EXPOSE 8761
CMD ["java", "-jar", "eureka-service-0.0.1-SNAPSHOT.jar"]