# Web-based-CV
 A demo for building web-based cv in microservices architecture, the application contains third party tools like redis and docker platform.
 Languages and Frameworks:
  1. Java 8+
  2. Spring Boot
  3. Spring Cloud
  4. Angular 6+
# Setup
 To run the system locally using the same database, you need to do the following:
   1. CREATE USER in MySql <br>
      CREATE USER 'sweelam'@'localhost' IDENTIFIED BY 'sweelam_224466';
   2. CREATE DATABASE <br>
      create database sweprofile;
   3. GRANT USER ACCESS <br>
      GRANT ALL ON `sweprofile`.* TO 'sweelam'@'localhost';
   4. Execute <strong>db-dump.sql</strong> file in database 
   5. If you need to change databse credentials, you can update <strong>properties</strong> table details 
 
# Running Steps
  To start the application through docker you need to start server shell file using <strong>./server-start.sh</strong>
      
