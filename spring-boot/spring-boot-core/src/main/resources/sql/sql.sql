-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: spring-boot-core
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `e_mail` varchar(45) DEFAULT NULL,
  `execute_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'srRnX','srrnx@mail.com','2020-10-06 10:41:29'),(2,'ZVytS','zvyts@mail.com','2020-10-06 10:41:29'),(3,'ybzjO','ybzjo@mail.com','2020-10-06 10:41:29'),(4,'mXGlj','mxglj@mail.com','2020-10-06 10:41:29'),(5,'Afzty','afzty@mail.com','2020-10-06 10:41:29'),(6,'Tltjt','tltjt@mail.com','2020-10-06 10:41:50'),(7,'IViHm','ivihm@mail.com','2020-10-06 10:41:50'),(8,'cKjNu','ckjnu@mail.com','2020-10-06 10:41:50'),(9,'FcPcI','fcpci@mail.com','2020-10-06 10:41:50'),(10,'esPcD','espcd@mail.com','2020-10-06 10:41:50'),(11,'QOILg','qoilg@mail.com','2020-10-06 10:42:42'),(12,'DImrK','dimrk@mail.com','2020-10-06 10:42:42'),(13,'SqzcA','sqzca@mail.com','2020-10-06 10:42:42'),(14,'AxWJF','axwjf@mail.com','2020-10-06 10:42:42'),(15,'MtLpx','mtlpx@mail.com','2020-10-06 10:42:42'),(16,'Qcmaz','qcmaz@mail.com','2020-10-06 10:43:02'),(17,'wKMRh','wkmrh@mail.com','2020-10-06 10:43:02'),(18,'VQjaW','vqjaw@mail.com','2020-10-06 10:43:02'),(19,'IruUr','iruur@mail.com','2020-10-06 10:43:02'),(20,'xQpjz','xqpjz@mail.com','2020-10-06 10:43:02');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-06 10:45:32
