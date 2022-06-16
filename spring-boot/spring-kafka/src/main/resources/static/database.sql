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
