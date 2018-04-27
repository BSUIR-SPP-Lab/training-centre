CREATE DATABASE  IF NOT EXISTS `training_service_db_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `training_service_db_test`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: training_service_db_test
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` (`application_id`, `student_id`, `course_id`) VALUES (1,41,2),(2,62,3),(3,66,4),(4,62,4),(5,61,5),(6,48,5),(7,67,3),(8,67,5),(9,64,2),(10,43,2),(11,64,9),(12,65,9);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` (`certificate_id`, `student_id`, `group_id`) VALUES (3,41,4),(8,61,10),(11,62,12),(5,63,8),(7,63,9),(9,63,10),(10,63,11),(6,64,8),(4,65,5),(12,67,13);
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
  KEY `fk_course_user1_idx` (`coordinator_id`),
  CONSTRAINT `FK_course_course_info` FOREIGN KEY (`course_info_id`) REFERENCES `course_info` (`course_info_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_user` FOREIGN KEY (`coordinator_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`course_id`, `course_info_id`, `coordinator_id`, `start`, `end`) VALUES (2,2,52,'2018-03-13','2018-03-24'),(3,1,44,'2018-02-12','2018-05-12'),(4,7,45,'2018-01-13','2018-01-13'),(5,9,50,'2018-02-18','2018-02-19'),(6,11,50,'2022-05-19','2066-05-19'),(7,3,45,'2342-12-08','2442-05-08'),(8,4,45,'2202-02-18','2203-04-12'),(9,2,50,'2012-02-12','2013-02-18'),(10,9,45,'2018-04-23','2019-01-04'),(11,5,52,'2018-03-12','2018-04-12'),(12,6,50,'2016-02-13','2017-02-13'),(13,8,45,'2018-03-13','2019-02-13');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_info`
--

LOCK TABLES `course_info` WRITE;
/*!40000 ALTER TABLE `course_info` DISABLE KEYS */;
INSERT INTO `course_info` (`course_info_id`, `name`, `description`) VALUES (1,'DotNet','Изучение dotNet технологий на углубленном уровне. '),(2,'!Java','Изучение устаревшей технологии Java на углубленном уровне.'),(3,'Python','Курс по языку программирования Python.'),(4,'Scala','Курс по языку программирования Scala.'),(5,'Курс смекалки','Курс развивающий вашу смекалку.'),(6,'Математика','Курс высшей математики.'),(7,'Кругозор','Курс расширяющий ваш кругозор.'),(8,'Музыкальные курсы','В данном курсе вас научат играть на различных музыкальных инструментах.'),(9,'Курсы рисования.','В данном курсе вас научат рисовать в различных стилях.'),(10,'Курс физики','Курс физики.'),(11,'Курс веб-технологий.','Изучение технологий ориентированных на веб.');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` (`group_id`, `course_id`, `coordinator_id`) VALUES (2,2,52),(3,2,52),(4,4,44),(5,5,50),(6,5,50),(7,5,50),(8,13,45),(9,12,50),(10,11,52),(11,9,50),(12,8,45),(13,8,45);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `training_service_db_test`.`group_BEFORE_INSERT` BEFORE INSERT ON `group` FOR EACH ROW
  BEGIN
    DECLARE user_count INT;
    SET user_count = (SELECT COUNT(*) FROM `user` WHERE (NEW.`coordinator_id` = `user`.`user_id`) AND (`user`.`role` = 'coordinator'));
    IF user_count != 1 THEN
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The specified user is not a student.';
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `training_service_db_test`.`group_BEFORE_UPDATE` BEFORE UPDATE ON `group` FOR EACH ROW
  BEGIN
    DECLARE user_count INT;
    SET user_count = (SELECT COUNT(*) FROM `user` WHERE (NEW.`coordinator_id` = `user`.`user_id`) AND (`user`.`role` = 'coordinator'));
    IF user_count != 1 THEN
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The specified user is not a student.';
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
INSERT INTO `solution` (`task_id`, `user_id`, `notes`, `filepath`, `teacher_notes`, `mark`, `upload_time`) VALUES (14,66,'Ширина Ленина умноженная на длину Ленина.',NULL,'Отлично',9,'2018-03-12 19:37:00'),(14,67,'Не знаю(',NULL,'Плохо',4,'2018-03-12 19:36:07'),(21,64,NULL,'/main/answer/file13.txt','Отлично',10,'2018-03-12 19:40:57'),(21,65,NULL,'/main/answer/file12.txt','Плохо',5,'2018-03-12 19:40:57'),(22,62,NULL,'/main/answer/file1.txt','Нормально',7,'2018-03-12 19:45:57'),(22,63,NULL,'/main/answer/file11.txt','Хорошо',9,'2018-03-12 19:45:57'),(25,61,NULL,'/main/answer/file1.txt','Отлично',9,'2018-03-12 19:41:01'),(25,62,NULL,'/main/answer/file14.txt','Отлично',10,'2018-03-12 19:45:57'),(26,41,NULL,'/main/answer/file1.txt','Хорошо',NULL,'2018-03-12 19:42:06'),(26,43,NULL,'/main/answer/file16.txt','Отлично',10,'2018-03-12 19:45:57'),(26,65,NULL,'/main/answer/file15.txt','Хорошо',8,'2018-03-12 19:45:57'),(26,66,NULL,'/main/answer/file17.txt','Плохо',7,'2018-03-12 19:45:57');
/*!40000 ALTER TABLE `solution` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `training_service_db_test`.`solution_BEFORE_INSERT` BEFORE INSERT ON `solution` FOR EACH ROW
  BEGIN
    IF ((NEW.`filepath` is null) AND (NEW.`notes` is NULL)) THEN
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Solution is empty';
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
INSERT INTO `student` (`student_id`) VALUES (41),(43),(48),(61),(62),(63),(64),(65),(66),(67);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
INSERT INTO `student_group` (`student_id`, `group_id`, `course_complete`) VALUES (41,2,0),(41,4,1),(65,4,0),(65,5,1),(63,6,0),(64,6,0),(66,6,0),(63,7,0),(63,8,1),(64,8,1),(63,9,1),(43,10,0),(61,10,1),(63,10,1),(61,11,0),(63,11,1),(62,12,1),(62,13,0),(67,13,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` (`task_id`, `teacher_id`, `group_id`, `task_info_id`, `upload_time`) VALUES (1,51,2,5,'2018-03-10 19:57:56'),(6,51,2,5,'2018-03-10 19:57:56'),(14,51,2,5,'2233-03-12 22:22:00'),(15,51,2,4,'1333-02-12 02:02:00'),(16,51,2,3,'0333-02-12 03:03:00'),(17,51,2,2,'0002-03-12 03:22:00'),(21,51,2,5,'0033-03-13 03:03:00'),(22,57,8,4,'2555-02-18 23:59:00'),(24,59,9,11,'2216-02-18 23:06:00'),(25,46,3,13,'2202-03-12 22:22:00'),(26,55,7,3,'0034-03-21 04:01:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_info`
--

LOCK TABLES `task_info` WRITE;
/*!40000 ALTER TABLE `task_info` DISABLE KEYS */;
INSERT INTO `task_info` (`task_info_id`, `name`, `body`) VALUES (2,'Проверка знаний по курсу тестирования. вопрос 1','Что такое тест?'),(3,'Проверка знаний по курсу НПО. Вопрос 1','Что такое надежность?'),(4,'Дополнительный вопрос по НПО','Отличие надежности от долговечности. '),(5,'!Экзаменационный вопрос по ЯП','Отличие процедур от функций.'),(6,'Вопрос ЦТ по математике ','Теорема Вейерштрасса'),(7,'Вопрос на смекалку ','Какое слово звучит неверно?'),(8,'Вопрос на смекалку ','Назовите пять дней, не называя чисел и названий.'),(9,'Вопрос на смекалку ','Кто может путешествовать по свету, оставаясь в одном и том же углу?'),(10,'Теорема Ролля','Докажите Теорему Ролля'),(11,'Теорема Коши','Докажите Теорему Коши'),(12,'ЯП описание','Опишите различия языков C# и Java.'),(13,'Дисциплины для поступления на ПОИТ','Назовите перечень дисциплин для поступления на ПОИТ.'),(14,'Площадь Ленина',' Как найти площадь Ленина? ');
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
INSERT INTO `teacher` (`teacher_id`, `group_id`) VALUES (46,2),(51,2),(46,3),(47,4),(51,5),(54,6),(55,7),(56,8),(57,8),(59,9),(60,10);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `training_service_db_test`.`teacher_BEFORE_INSERT` BEFORE INSERT ON `teacher` FOR EACH ROW
  BEGIN
    DECLARE user_count INT;
    SET user_count = (SELECT COUNT(*) FROM `user` WHERE (NEW.`teacher_id` = `user`.`user_id`) AND (`user`.`role` = 'teacher'));
    IF user_count != 1 THEN
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The specified user is not a teacher.';
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `training_service_db_test`.`teacher_BEFORE_UPDATE` BEFORE UPDATE ON `teacher` FOR EACH ROW
  BEGIN
    DECLARE user_count INT;
    SET user_count = (SELECT COUNT(*) FROM `user` WHERE (NEW.`teacher_id` = `user`.`user_id`) AND (`user`.`role` = 'teacher'));
    IF user_count != 1 THEN
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The specified user is not a teacher.';
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `login`, `password`, `role`, `email`, `phone`, `first_name`, `last_name`) VALUES (41,'petrov','$2a$10$pgsaPlhuTvtbcekQ9ivXc.5b5.IYL5Su8ulXCxqZ/em21ws58y3Ki','student','petr123@gmail.com','375284466888','Петрo','Петров'),(43,'ivan18','$2a$10$ZvvnFNO3UmlYTOfDxEaVEuTBGXkYRL78aWxDVHFZgMW9uJ88yqqIC','student','ivan@ya.ru','5556689','Иван','Иванов'),(44,'sereew89','$2a$10$p6TG5bllsrUrudf7x3/IvOTS5faUoRZd7w9VFYCrnm5A0v4dVrD9u','coordinator','sergeev@open.by','5551022','Сергей ','Сергеев'),(45,'alex15','$2a$10$EsCqyX.aQjh2098D8/XayuVVJldFEemC6rFSe3amtz.0w.CGBDEt2','coordinator','alex@tut.by','4441382','Алексей','Алексеев'),(46,'vlas','$2a$10$LyNpLaIqBgtYTBWl3YYCFu.j518fzeaEmWzmvymoubS5THxRLQdoK','teacher','vlas@gmail.com','99994523','Владислав','Владиславов'),(47,'dasha15','$2a$10$49WLSbO7eW6ULfYGaPwcpODpgHPcaEPmswBZ/O011k.yIe2u5bCu2','teacher','dash@gmail.com','1883592356','Даша','Дашина'),(48,'cat1566','$2a$10$4E6uT21RneB9FkHJ.2DZP.vwLo/GgXHAtCNORPCqxpUVvyXD4Qoqe','user','cat@ya.ru','1688442','Катя','Катина'),(49,'coo2141','$2a$10$IlAy9HOKO.WgsrpPN.xBfu6ySTfMg8CX24WlZl6dUCCUHooWg1XFy','student','col2141@gmail.com','5551813','Коля','Колин'),(50,'olga19','$2a$10$MuBazognyaXr2er2wKo9iuWHFIyRnxopmg5OYsRSwijAaZ.ssEKMi','coordinator','ola@gmail.com','4847756','Оля ','Олина'),(51,'teacher','$2a$10$MuBazognya2er2wffKo9iuWHFIyRnxopmg5OYsRSwijAaZ.ssEKMi','teacher','!teacher@gmail.com','8800553522','!Денис','!Денисов'),(52,'coordinator','$2a$10$MuBazognyaXr2er2wKo9iuWHFIyRnxopmg5OYsRSwijAaZ.ssEKMi','coordinator','!coordinator@gmail.com','2265816','!Марина','!Маринкова'),(53,'vasya228','$2a$10$rSFk9Wk8TccqYOH3GVrOTO/md2pSfm9RzLavWfiqgzZgZW1LGyBEW','student','vasya@gmail.com','2386262','Вася','Васин'),(54,'olegoff','$2a$10$EKdWiUffsYlcBQo9UQ4sr.fuLMKyt9MA0Kz4ytOpRxIb2nXokgDwu','teacher','oleg@go.com','8439222','Олег','Олегов'),(55,'artem12mm','$2a$10$fsF9jZIKAYtyBuwBpVSzgOLhvq/X5zdTnIMcAH.CdF/EIly0AcEEO','teacher','art@ya.ru','4445776','Артем','Артемов'),(56,'alin','$2a$10$VuKpbCvTgkMsFYuBurp/Ze2rLbG8.ACzn2L1srUSZnPtnxMasxzEe','teacher','alin@gmail.com','16562369','Алина','Алинина'),(57,'tanya','$2a$10$WOHJkRhovFQd0BAB1mw1p./U6hiWQvQb6psSzHTSz.HjUApdPxeGG','teacher','tanya@gmail.com','7449966','Таня','Танина'),(58,'galy','$2a$10$045UFTTiMQWSBpM2gps1b.0vqcQp.TFipcGPP0ZqRpn/Ebi3adX2e','teacher','galy@gmail.com','7745655','Галина','Галинова'),(59,'sashok','$2a$10$iCNHLbXHXZlbkdU0T8.sxe3ARz7edzm5Y2mBMyVM6Ll2jSMp/Byke','teacher','sasha@gmail.com','7844889','Саша ','Сашин'),(60,'catKot','$2a$10$5JQ8AADcVBM9pPhemA5xwOF2uID2XEDp54ASJamyYIPn0HbXx9E5u','teacher','catKot@gmail.com','7877500','Катя','Кобкина'),(61,'valya','$2a$10$95w3qtmy6adJAKyn3utuTu50ndqkdTWTikgHR2XKxQjALJVh/yCZK','student','valya@gs.com','6660506','Валентин','Валентинов'),(62,'kiri','$2a$10$Sytwk1pAs8OpYl.D2TytSuNhXut/Irv4KK6md.CJciGHzbozAxJq6','student','kirill@gmail.com','7458566','Кирилл','Киров'),(63,'sid','$2a$10$Ee.YQeme3R7BXp9rRF738erneYqQne37QAmO0xtnhaE4WBZdCZEfm','student','sid@gmail.com','5588999','Сидор','Сидоров'),(64,'nasty','$2a$10$zlwo1SHIyIR.etQpm2Z/suRgnVhJ1dmlWHzXG.OqOiS0yLOWsJAZu','student','nasty@gmail.com','7799444','Настя','Иванова'),(65,'ula','$2a$10$9/8BL84TBzYDaW8hBw2VK.9PuHJvtrkPaeKEvLtGNH4pNLjKapt2u','student','ula@gmail.com','1455999','Юля','Юлина'),(66,'gora','$2a$10$Wx07N7cpGF.H/Q47T3IRxeNR1SPHURfw1o7/4zNwzRhfzhgWDLzAi','student','gora@gmail.com','5150033','Жора','Жорин'),(67,'jeko','$2a$10$qKBpVu.cxpx7S90SCJdyx.0VRzYnUwJ4bS4E/FOxaTSw1Abz7BW2S','student','jeka@gmail.com','5512399','Женя','Женина');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `training_service_db_test`.`user_BEFORE_INSERT` BEFORE INSERT ON `user` FOR EACH ROW
  BEGIN
    IF (NEW.`role` = 'student') THEN
      IF ((SELECT COUNT(*) FROM `student` WHERE `student`.`student_id` = NEW.`user_id`) = 0) THEN
        INSERT INTO `student` (`student_id`) VALUES (NEW.`user_id`);
      END IF;
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `training_service_db_test`.`user_BEFORE_UPDATE` BEFORE UPDATE ON `user` FOR EACH ROW
  BEGIN
    IF (NEW.`role` = 'student') THEN
      IF ((SELECT COUNT(*) FROM `student` WHERE `student`.`student_id` = NEW.`user_id`) = 0) THEN
        INSERT INTO `student` (`student_id`) VALUES (NEW.`user_id`);
      END IF;
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'training_service_db_test'
--

--
-- Dumping routines for database 'training_service_db_test'
--
/*!50003 DROP FUNCTION IF EXISTS `CheckUploadTime` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `CheckUploadTime`(id_user INT , id_task INT) RETURNS tinyint(1)
  BEGIN
    DECLARE deadline datetime;
    DECLARE upload_time datetime;
    SET deadline =  (SELECT `deadline` FROM `task` WHERE `task_id` = `id_task`);
    SET upload_time = (SELECT `upload_time` FROM `solution` WHERE   ( (`task_id` = `id_task`) && (`user_id` = `id_user`)) );
    IF upload_time < deadline THEN
      RETURN 0b1; /*true */
    ELSE
      RETURN 0b0;/*false */
    END IF;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-20  0:15:42
