FROM java:openjdk-8-alpine
LABEL author="Mohamed Sweelam"
LABEL version="1.0"
LABEL description="cv portal"
COPY ./target/cv-portal-1.0.0-exec.jar cv-portal-1.0.0-exec.jar
EXPOSE 9090
CMD ["java", "-jar", "/cv-portal-1.0.0-exec.jar"]
