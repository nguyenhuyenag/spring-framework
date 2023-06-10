DROP DATABASE IF EXISTS database_1;
DROP DATABASE IF EXISTS database_2;

CREATE DATABASE IF NOT EXISTS database_1;
CREATE DATABASE IF NOT EXISTS database_2;

USE database_1;
CREATE TABLE IF NOT EXISTS `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(20) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `execute_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

USE database_2;
CREATE TABLE IF NOT EXISTS `user` (
	`id` int NOT NULL AUTO_INCREMENT,
  	`name` varchar(45) DEFAULT NULL,
  	`email` varchar(15) NOT NULL UNIQUE,
  	`execute_time` datetime DEFAULT NULL,
  	PRIMARY KEY (`id`)
);
