-- DROP DATABASE IF EXISTS brute_force_authen;
-- CREATE DATABASE brute_force_authen;

USE brute_force_authen;

create table `user` (
	`user_id` bigint not null auto_increment,
	`username` varchar(36) not null,
	`password` varchar(128) not null,
	`enabled` tinyint(1) not null default 0,
	`account_non_locked` tinyint(1) not null default 0,
	`failed_attempt` int(4) not null default 0,
	`lock_time` datetime,
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
	('admin','$2a$10$9iZ5IH756P2K7GvRJvZv8.WrPQ65qeOh.u.Ps.o06IQsbR9lyFCeW'),
	('user1','$2a$10$9iZ5IH756P2K7GvRJvZv8.WrPQ65qeOh.u.Ps.o06IQsbR9lyFCeW'),
	('test','$2a$10$9iZ5IH756P2K7GvRJvZv8.WrPQ65qeOh.u.Ps.o06IQsbR9lyFCeW');

