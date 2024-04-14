CREATE table `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `uuid` varchar(255) NOT NULL,
    `username` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `enabled` TINYINT(1) DEFAULT '0',
    PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
);

CREATE TABLE `refresh_token` (
     `id` int NOT NULL AUTO_INCREMENT,
     `username` varchar(255) DEFAULT NULL,
     `token` varchar(255) DEFAULT NULL,
     `expiry_date` datetime DEFAULT NULL,
     PRIMARY KEY (`id`),
     UNIQUE KEY `token_UNIQUE` (`token`)
);