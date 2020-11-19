CREATE DATABASE IF NOT EXISTS `spring_boot_core` ;

USE spring_boot_core;

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `e_mail` varchar(45) DEFAULT NULL,
  `execute_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
