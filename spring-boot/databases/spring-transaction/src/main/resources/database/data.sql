CREATE DATABASE IF NOT EXISTS `spring_transaction`;

USE spring_transaction;

CREATE TABLE IF NOT EXISTS `user` (
	`id` int NOT NULL AUTO_INCREMENT,
  	`name` varchar(45) DEFAULT NULL,
  	`email` varchar(15) NOT NULL UNIQUE,
  	`execute_time` datetime DEFAULT NULL,
  	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(20) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `execute_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);


