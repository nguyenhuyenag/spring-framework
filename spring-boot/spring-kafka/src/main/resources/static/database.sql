DROP TABLE IF EXISTS `lorem_ipsum`;
CREATE TABLE `lorem_ipsum` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `status` smallint DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `receive_message`;
CREATE TABLE `receive_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `listener` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
