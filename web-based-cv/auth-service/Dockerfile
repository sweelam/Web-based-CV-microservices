FROM java:openjdk-8-alpine
LABEL author="Mohamed Sweelam"
LABEL version="1.0"
LABEL description="cv authintication service"
COPY ./target/auth-service-1.0.0-exec.jar auth-service-1.0.0-exec.jar
EXPOSE 8060
CMD ["java", "-jar", "/auth-service-1.0.0-exec.jar"]
