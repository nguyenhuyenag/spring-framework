CREATE DATABASE `spring_boot` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE spring_boot;

CREATE TABLE `user` (
  `id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`,`username`)
);

INSERT INTO `user` VALUES
(1,'huyennv','$2a$10$z2H8EgG1uqgRRDXJV3uDrOOAyNn5IP1bc4bH/b3KJ8DGmoWmtd1kq','Nguyễn Văn Huyện','huyennv@gmail.com','admin'),
(2,'tran_van','$2a$10$DafpIWZviTmd2DrjJkr9uexnEURDhfMaq/GvPTycTNe1TuGvru0BO','Nguyen Van Tran','tran@gmail.com','user');