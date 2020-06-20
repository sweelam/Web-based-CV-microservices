FROM java:openjdk-8-alpine
LABEL author="Mohamed Sweelam"
LABEL version="1.0"
LABEL description="zuul server"
COPY ./target/zuul-service-0.0.1-SNAPSHOT.jar ./
EXPOSE 8081
CMD ["java", "-jar", "zuul-service-0.0.1-SNAPSHOT.jar"]
