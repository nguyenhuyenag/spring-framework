CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `failed_counter` int DEFAULT '0',
  `login_disabled` tinyint(1) DEFAULT '0',
  `time_login_disabled` datetime DEFAULT NULL,
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

INSERT INTO `user` VALUES (1,'huyennv','$2a$10$13HWiq1Z0S3vRSd836s3H.0GmceNFjo8HpIOTbZDD4/8KhYAU.yAq',4,0,'2022-01-06 11:44:26'),(2,'dev1','$2a$10$13HWiq1Z0S3vRSd836s3H.0GmceNFjo8HpIOTbZDD4/8KhYAU.yAq',0,0,NULL),(3,'user','$2a$10$13HWiq1Z0S3vRSd836s3H.0GmceNFjo8HpIOTbZDD4/8KhYAU.yAq',0,0,NULL);

INSERT INTO `role` VALUES (1,'ADMIN','Admin role'),(2,'USER','User role');

INSERT INTO `user_roles` VALUES (1,1),(1,2),(2,2);
