CREATE DATABASE  IF NOT EXISTS `sweprofile` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `sweprofile`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: sweprofile
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.34-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `my_info`
--

DROP TABLE IF EXISTS `my_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_info` (
  `id` int(11) NOT NULL,
  `FULL_NAME` varchar(400) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(400) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `job_title` varchar(400) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(400) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_of_birth` varchar(400) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `job_start_date` varchar(400) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `job_end_date` varchar(400) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_info`
--

LOCK TABLES `my_info` WRITE;
/*!40000 ALTER TABLE `my_info` DISABLE KEYS */;
INSERT INTO `my_info` VALUES (1,'Mohamed Sweelam','+20 012 71459 665','Java Software Engineering Consultant ','MohamedSweelam@fci.helwan.edu.eg','26, Feb 1993 ','June 2016','Current'),(2,'Mohamed Sweelam','+20 012 71459 665','Java Senior Consultant ','MohamedSweelam@fci.helwan.edu.eg','26, Feb 1993 ','Jan 2016','June 2017'),(3,'Mohamed Sweelam','+20 012 71459 665','Junior Software Developer','MohamedSweelam@fci.helwan.edu.eg','26, Feb 1993 ','Jan 2015','June 2016');
/*!40000 ALTER TABLE `my_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_jobs`
--

DROP TABLE IF EXISTS `my_jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_jobs` (
  `id` int(11) NOT NULL,
  `title` varchar(400) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `company` varchar(400) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `job_description` text COLLATE utf8mb4_unicode_ci,
  `info_ref` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `info_ref` (`info_ref`),
  CONSTRAINT `my_jobs_ibfk_1` FOREIGN KEY (`info_ref`) REFERENCES `my_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_jobs`
--

LOCK TABLES `my_jobs` WRITE;
/*!40000 ALTER TABLE `my_jobs` DISABLE KEYS */;
INSERT INTO `my_jobs` VALUES (1,'Software Engineer','2015-07-01','2016-06-01','Buducloud','Working as junior in building ERP system, Developing a smart meter system,Contributing in the Business Analysis and System Design phases, Handling support with customers for production technical issues',3),(2,'Java Senior Developer','2016-06-01','2017-12-01','Turkey Remote','Working as a Senior Java Back-end developer, Contributing in the Business\nAnalysis and System Design phases, Designing and Developing Web App',2),(3,'Java Senior Consultant ','2016-06-01',NULL,'egabi','Working as a Team Leader leading the Development Team, Handling Support\nand Technical Issues reported from customer-side, Integrating developed\nwork with the on-site development team, Handling Data Migration & Integration\nwith 3rd party tools.',1);
/*!40000 ALTER TABLE `my_jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temp`
--

DROP TABLE IF EXISTS `temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp` (
  `id` int(11) NOT NULL,
  `value` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temp`
--

LOCK TABLES `temp` WRITE;
/*!40000 ALTER TABLE `temp` DISABLE KEYS */;
INSERT INTO `temp` VALUES (1,'data','test data'),(2,'big bang','2 test data');
/*!40000 ALTER TABLE `temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_mobile`
--

DROP TABLE IF EXISTS `user_mobile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_mobile` (
  `id` int(11) NOT NULL,
  `value` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_mobile_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `my_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_mobile`
--

LOCK TABLES `user_mobile` WRITE;
/*!40000 ALTER TABLE `user_mobile` DISABLE KEYS */;
INSERT INTO `user_mobile` VALUES (1,'02121212',1),(2,'02121212',2),(3,'02121212',3);
/*!40000 ALTER TABLE `user_mobile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_skills`
--

DROP TABLE IF EXISTS `user_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_skills` (
  `user_id` int(11) NOT NULL,
  `skills` varchar(190) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_skills`
--

LOCK TABLES `user_skills` WRITE;
/*!40000 ALTER TABLE `user_skills` DISABLE KEYS */;
INSERT INTO `user_skills` VALUES (1,'Software Development',1),(3,'Software Architecture Design',2),(2,'Data Science',3),(4,'Business Analysis',4);
/*!40000 ALTER TABLE `user_skills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-25 18:03:12
