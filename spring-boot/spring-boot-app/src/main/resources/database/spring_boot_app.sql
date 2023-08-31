DROP TABLE IF EXISTS `file_store`;

CREATE TABLE `file_store` (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_id` varchar(50) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_content` longtext,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`file_id`)
);
