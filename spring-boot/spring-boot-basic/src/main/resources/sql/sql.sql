CREATE DATABASE IF NOT EXISTS `spring_boot_learning` ;

USE `spring_boot_learning`;

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `e_mail` varchar(45) DEFAULT NULL UNIQUE,
  `execute_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
