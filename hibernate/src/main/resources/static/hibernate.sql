CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `mysql_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `Age` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `camelCase` varchar(45) DEFAULT NULL,
  `card_number` varchar(45) DEFAULT NULL,
  `language_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `mysql_name` VALUES (1,'Java','24','192.168.1.1','Java','01782231','vie');
