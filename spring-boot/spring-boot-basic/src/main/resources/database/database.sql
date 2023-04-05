CREATE DATABASE IF NOT EXISTS `spring_boot_basic` ;

USE `spring_boot_basic`;

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL UNIQUE,
  `execute_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
