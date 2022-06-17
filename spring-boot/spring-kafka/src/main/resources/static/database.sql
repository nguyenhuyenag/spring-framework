CREATE TABLE `data_received` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `listener` varchar(5) DEFAULT NULL,
  `data_code` varchar(45) DEFAULT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `status` smallint DEFAULT '0',
  `content` longtext DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`, `code`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
);
