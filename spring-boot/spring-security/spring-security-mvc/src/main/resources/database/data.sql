DROP DATABASE IF EXISTS spring_security_mvc;
CREATE DATABASE spring_security_mvc;
USE spring_security_mvc;

create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);

create table `user` (
	`user_id` bigint not null auto_increment,
	`username` varchar(36) not null,
	`password` varchar(128) not null,
	`enabled` int(1) not null default 0,
	primary key (`user_id`),
	unique (`user_id`, `username`)
);

create table `role` (
  `role_id` bigint not null,
  `role_name` varchar(30) not null,
  primary key (`role_id`),
  unique (`role_name`)
);

create table `user_roles` (
  `id` bigint not null auto_increment,
  `user_id` bigint not null,
  `role_id` bigint not null,
  primary key (`id`),
  foreign key (`user_id`) references `user` (`user_id`),
  foreign key (`role_id`) references `role` (`role_id`)
);

DROP TABLE IF EXISTS `auth_history`;
CREATE TABLE `auth_history`  (
    `token_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `login_at` timestamp NULL DEFAULT NULL,
    `expired_time` bigint(20) NULL DEFAULT NULL,
    PRIMARY KEY (`token_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

insert into `role` values 
	(1,'ROLE_ADMIN'),
	(2,'ROLE_MOD'),
	(3,'ROLE_USER');

insert into `user`(username, password) values 
	('admin','$2a$10$9iZ5IH756P2K7GvRJvZv8.WrPQ65qeOh.u.Ps.o06IQsbR9lyFCeW'),
	('user','$2a$10$9iZ5IH756P2K7GvRJvZv8.WrPQ65qeOh.u.Ps.o06IQsbR9lyFCeW');

