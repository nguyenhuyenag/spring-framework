DROP DATABASE IF EXISTS spring_security_jsp;

CREATE DATABASE spring_security_jsp;

USE spring_security_jsp;

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

insert into `role` values 
	(1,'ROLE_ADMIN'),
	(2,'ROLE_MOD'),
	(3,'ROLE_USER');

insert into `user`(username, password) values 
	('admin','$2a$10$gT3kPX1zM27rnGdCTwwAJ.OaO0gdDg9eb3YCkr7fySFl2n3uB27yq'),
	('user','$2a$10$gT3kPX1zM27rnGdCTwwAJ.OaO0gdDg9eb3YCkr7fySFl2n3uB27yq');

