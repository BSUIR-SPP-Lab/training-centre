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

-- Dump completed on 2018-03-12  0:44:27
