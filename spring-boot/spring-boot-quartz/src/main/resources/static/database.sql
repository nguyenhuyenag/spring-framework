CREATE DATABASE IF NOT EXISTS quartz_scheduler;

USE quartz_scheduler;

DROP TABLE IF EXISTS quartz;

CREATE TABLE IF NOT EXISTS `quartz` (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `text` varchar(45) DEFAULT NULL,
  `excuse_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
