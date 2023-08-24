-- DROP TABLE IF EXISTS t_user;

CREATE TABLE IF NOT EXISTS t_user (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(5) DEFAULT NULL,
  `age` tinyint NOT NULL DEFAULT '0',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);
