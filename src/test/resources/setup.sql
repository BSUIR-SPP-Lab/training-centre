DROP DATABASE IF EXISTS `training_service_db_test`;
CREATE DATABASE  IF NOT EXISTS `training_service_db_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `training_service_db_test`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: training_service_db_test
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `application_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int(10) unsigned NOT NULL,
  `course_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`application_id`),
  KEY `IXFK_application_course` (`course_id`),
  KEY `IXFK_application_student` (`student_id`),
  CONSTRAINT `FK_application_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_application_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certificate` (
  `certificate_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int(10) unsigned NOT NULL,
  `group_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`certificate_id`),
  KEY `IXFK_certificate_student` (`student_id`,`group_id`),
  KEY `FK_certificate_student` (`group_id`,`student_id`),
  CONSTRAINT `FK_certificate_student` FOREIGN KEY (`group_id`, `student_id`) REFERENCES `student_group` (`group_id`, `student_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_info_id` int(10) unsigned NOT NULL,
  `coordinator_id` int(10) unsigned NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `IXFK_course_course_info` (`course_info_id`),
  KEY `fk_course_user_idx` (`coordinator_id`),
  CONSTRAINT `FK_course_course_info` FOREIGN KEY (`course_info_id`) REFERENCES `course_info` (`course_info_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_user` FOREIGN KEY (`coordinator_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (2,2,52,'2018-03-13','2018-03-24');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_info`
--

DROP TABLE IF EXISTS `course_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_info` (
  `course_info_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  PRIMARY KEY (`course_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_info`
--

LOCK TABLES `course_info` WRITE;
/*!40000 ALTER TABLE `course_info` DISABLE KEYS */;
INSERT INTO `course_info` VALUES (1,'111111','FC'),(2,'!COURSE_INFO','!COURSE_INFO'),(3,'113','123');
/*!40000 ALTER TABLE `course_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `group_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_id` int(10) unsigned NOT NULL,
  `coordinator_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`group_id`),
  KEY `IXFK_group_course` (`course_id`),
  KEY `IXFK_group_user` (`coordinator_id`),
  CONSTRAINT `FK_group_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_group_user` FOREIGN KEY (`coordinator_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (2,2,52);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solution`
--

DROP TABLE IF EXISTS `solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solution` (
  `task_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `notes` text,
  `filepath` text,
  `teacher_notes` text,
  `mark` int(10) unsigned DEFAULT NULL,
  `upload_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`,`user_id`),
  KEY `IXFK_solution_task` (`task_id`),
  KEY `FK_solution_user_idx` (`user_id`),
  CONSTRAINT `FK_solution_task` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_solution_user` FOREIGN KEY (`user_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solution`
--

LOCK TABLES `solution` WRITE;
/*!40000 ALTER TABLE `solution` DISABLE KEYS */;
/*!40000 ALTER TABLE `solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `IXFK_student_user` (`student_id`),
  CONSTRAINT `FK_student_user` FOREIGN KEY (`student_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (43),(48);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_group`
--

DROP TABLE IF EXISTS `student_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_group` (
  `student_id` int(10) unsigned NOT NULL,
  `group_id` int(10) unsigned NOT NULL,
  `course_complete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`group_id`,`student_id`),
  KEY `IXFK_student_group_group` (`group_id`),
  KEY `IXFK_student_group_student` (`student_id`),
  CONSTRAINT `FK_student_group_group` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_group_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_group`
--

LOCK TABLES `student_group` WRITE;
/*!40000 ALTER TABLE `student_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `task_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `teacher_id` int(10) unsigned NOT NULL,
  `group_id` int(10) unsigned NOT NULL,
  `task_info_id` int(10) unsigned NOT NULL,
  `upload_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`),
  KEY `IXFK_task_task_info` (`task_info_id`),
  KEY `IXFK_task_teacher` (`group_id`,`teacher_id`),
  CONSTRAINT `FK_task_task_info` FOREIGN KEY (`task_info_id`) REFERENCES `task_info` (`task_info_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_task_teacher` FOREIGN KEY (`group_id`, `teacher_id`) REFERENCES `teacher` (`group_id`, `teacher_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,51,2,5,'2018-03-10 19:57:56'),(6,51,2,5,'2018-03-10 19:57:56'),(14,51,2,5,'2233-03-12 22:22:00'),(15,51,2,4,'1333-02-12 02:02:00'),(16,51,2,3,'0333-02-12 03:03:00'),(17,51,2,2,'0002-03-12 03:22:00'),(21,51,2,5,'0033-03-13 03:03:00');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_info`
--

DROP TABLE IF EXISTS `task_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_info` (
  `task_info_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `body` text NOT NULL,
  PRIMARY KEY (`task_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_info`
--

LOCK TABLES `task_info` WRITE;
/*!40000 ALTER TABLE `task_info` DISABLE KEYS */;
INSERT INTO `task_info` VALUES (2,'55522','555'),(3,'66622','666'),(4,'1232','12311'),(5,'!TASK','!TASK');
/*!40000 ALTER TABLE `task_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` int(10) unsigned NOT NULL,
  `group_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`group_id`,`teacher_id`),
  KEY `IXFK_teacher_group` (`group_id`),
  KEY `IXFK_teacher_user` (`teacher_id`),
  CONSTRAINT `FK_teacher_group` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_teacher_user` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (51,2);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` char(64) NOT NULL,
  `role` enum('user','student','teacher','coordinator','admin') NOT NULL,
  `email` varchar(256) NOT NULL,
  `phone` char(16) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (23,'rwer','$2a$10$AaC7LPyclUPcwtC6GmaLzOGZlCv4e/wQ92WMQfvPR6WD4Lx38ZDTS','admin','13@gmail.com','wqerqr','13231','wr124e1',1),(25,'ferw','$2a$10$I5hF0QOT5ytNYu/C5YXQk.eH9e./7nYn7tX4jwFF49G7mGw3GHfdO','admin','f@f.com','wer','12311','123',1),(26,'134124','$2a$10$m0.OTOlP7RfucMyNFzMSMO1yiMgK1ESNqNDMwrJFm1sY8mkd8Q4BO','student','123@fsf','qweqwe','132123','123123',1),(27,'DFSFDS','$2a$10$2MQfUQWtQo7Pcx59lyKKYOyw3pE9Z04MgnhOERR.f5Rvd.ISxLvO2','admin','wet@FSDF','SDFSDF','stwet123','ertetr',1),(28,'223','$2a$10$ZFxrQ6VcvugL8lakC2qiMO2EH5ricUhDsGUmRI4rZcNmd3rD2RZ36','student','2@FSD','2323','123132','132132',1),(29,'DSF','$2a$10$wXupbFCvimm0v/pZdd1GU.9Y29MzQPxqK4R2hX3zIhJxK0uluXoVq','student','CZCQ2@FSD','DFS','ZXXCXZC','XZCZX',1),(33,'sdfsdfxcvxcv','$2a$10$Hhq1DZNThuotlTdpOkV2YuK09t024BDaEe/9u9GK4JR6J3XY9KK/2','student','fdf@fdsfxcvxcv','fsdsdfxcv','fdfdfvcxv','dfdg',1),(35,'sdfsfd','$2a$10$CW1TxHMnLERVeyY1Ml0YnetoBafKh1ImcVF0TD.NEYrdbFynoD8oO','admin','ff@fsd','fsdsfd','fdsf','sdfsfd',1),(37,'4124','$2a$10$9WHI0n72DzMtonQwXW62O.1uPwZQIzGC8EsurGQxZWRG89MsiDg4m','teacher','123123@sdf','fd','123123','123123',1),(40,'dfsf','$2a$10$nL/8DOhnUk58JpHrC40ePOQUZm6YLPBabX/nuNtIJBvbgf7P7LL8W','admin','1@13','sfd','3123','123123',1),(41,'sdf12','$2a$10$pgsaPlhuTvtbcekQ9ivXc.5b5.IYL5Su8ulXCxqZ/em21ws58y3Ki','student','132@fsd','qwe','321','123',1),(43,'qwrq','$2a$10$ZvvnFNO3UmlYTOfDxEaVEuTBGXkYRL78aWxDVHFZgMW9uJ88yqqIC','teacher','123@fsd','sdf','132fsdf','13241g',1),(44,'sdf','$2a$10$p6TG5bllsrUrudf7x3/IvOTS5faUoRZd7w9VFYCrnm5A0v4dVrD9u','coordinator','__dfsdf@df','__','__fdsfd','__dfsf',1),(45,'1____','$2a$10$EsCqyX.aQjh2098D8/XayuVVJldFEemC6rFSe3amtz.0w.CGBDEt2','admin','1____@fsd1','1____','1____','1____',1),(46,'1____1____1____','$2a$10$LyNpLaIqBgtYTBWl3YYCFu.j518fzeaEmWzmvymoubS5THxRLQdoK','teacher','1____1____@GDF','1____1____','1____1____','1____1____1____',1),(47,'dsfsdf','$2a$10$49WLSbO7eW6ULfYGaPwcpODpgHPcaEPmswBZ/O011k.yIe2u5bCu2','admin','213@sfrd','sdfsdf','132132','132132',1),(48,'test','$2a$10$4E6uT21RneB9FkHJ.2DZP.vwLo/GgXHAtCNORPCqxpUVvyXD4Qoqe','user','test@test','test','test','test',1),(49,'testtesttest','$2a$10$IlAy9HOKO.WgsrpPN.xBfu6ySTfMg8CX24WlZl6dUCCUHooWg1XFy','student','testtesttest@test','test','testtest','testtest',1),(50,'test@gmail.com','$2a$10$MuBazognyaXr2er2wKo9iuWHFIyRnxopmg5OYsRSwijAaZ.ssEKMi','admin','test@gmail.com','test@gmail.com','test@gmail.com','test@gmail.com',1),(51,'!TEACHER','!TEACHER','teacher','!TEACHER','!TEACHER','!TEACHER','!TEACHER',1),(52,'!COORDINATOR','!COORDINATOR','coordinator','!COORDINATOR','!COORDINATOR','!COORDINATOR','!COORDINATOR',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-11  4:37:25
