CREATE SCHEMA IF NOT EXISTS `spring_security_jwt`;

USE `spring_security_jwt`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user` VALUES
(1,'user','$2a$10$13HWiq1Z0S3vRSd836s3H.0GmceNFjo8HpIOTbZDD4/8KhYAU.yAq'),
(2,'admin','$2a$10$fbfO9jGJaEy6tqCeyRotVOtNa0pvZ6FdPMkV/xPls0DcsBmicKCvy'),
(3,'huyennv','$2a$10$CypmBnDVx1E6A2wxeGHUI.CSiml7H7PLW41N7Xjkm2vDovhWI5lcK');

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `role` VALUES
(1,'ADMIN','Admin role'),
(2,'USER','User role');

CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
);
INSERT INTO `user_roles` VALUES (1,1), (2,2), (3,1), (3,2);
