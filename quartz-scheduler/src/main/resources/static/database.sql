CREATE SCHEMA quartz_scheduler;
USE quartz_scheduler;
CREATE TABLE `quartz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(45) DEFAULT NULL,
  `excuse_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);