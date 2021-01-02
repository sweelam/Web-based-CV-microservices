# Web-based-CV
 A demo for building web-based cv in microservices architecture, in Java 8 using Spring boot framework and Angular 6+
 Application may contains third party tools like redis and docker platform
 
# Setup
 To run the system locally using the same database, you need to do the following:
   1. CREATE USER in MySql <br>
      CREATE USER 'sweelam'@'localhost' IDENTIFIED BY 'sweelam_224466';
   2. CREATE DATABASE <br>
      create database sweprofile;
   3. GRANT USER ACCESS <br>
      GRANT ALL ON `sweprofile`.* TO 'sweelam'@'localhost';
   4. Execute db-dump.sql file in database 
   5. If you need to change databse credentials, you can update <strong>properties</strong> table details 
 
# Running Steps
  To start the application through docker you need to do the following:
    1. start server shell file <br>
      ./server-start.sh
