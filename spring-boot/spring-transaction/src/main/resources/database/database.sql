CREATE DATABASE IF NOT EXISTS `spring_transaction`;

USE `spring_transaction`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id` int NOT NULL AUTO_INCREMENT,
  	`name` varchar(45) DEFAULT NULL,
  	`email` varchar(15) DEFAULT NULL UNIQUE,
  	`execute_time` datetime DEFAULT NULL,
  	PRIMARY KEY (`id`)
);
