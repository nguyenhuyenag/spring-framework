 CREATE DATABASE spring_security_jwt;
 
 USE spring_security_jwt;
 
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
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

INSERT INTO `user` (`username`, `password`) VALUES 
	('huyennv','$2a$10$13HWiq1Z0S3vRSd836s3H.0GmceNFjo8HpIOTbZDD4/8KhYAU.yAq'),
	('dev1','$2a$10$13HWiq1Z0S3vRSd836s3H.0GmceNFjo8HpIOTbZDD4/8KhYAU.yAq'),
	('user','$2a$10$13HWiq1Z0S3vRSd836s3H.0GmceNFjo8HpIOTbZDD4/8KhYAU.yAq');

INSERT INTO `role` VALUES 
	(1,'ADMIN','Admin role'),
	(2,'MOD','Mod role'),
	(3,'USER', 'User role');

INSERT INTO `user_roles` VALUES (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 3);
