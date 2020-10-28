FROM java:openjdk-8-alpine
LABEL author="Mohamed Sweelam"
LABEL version="1.0"
LABEL description="admin server"
COPY ./target/admin-server-0.0.1-SNAPSHOT.jar ./
EXPOSE 8888
CMD ["java", "-jar", "admin-server-0.0.1-SNAPSHOT.jar"]
