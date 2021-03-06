use spring_security;

create table `user` (
	`user_id` bigint not null auto_increment,
	`username` varchar(36) not null,
	`password` varchar(128) not null,
	`enabled` bit(1) not null default 0,
	primary key (`user_id`),
	unique (`user_id`, `user_name`)
);

create table `role` (
  `role_id` bigint not null,
  `role_name` varchar(30) not null,
  primary key (`role_id`),
  unique (`role_name`)
);

create table `user_role` (
  `id` bigint not null auto_increment,
  `user_id` bigint not null,
  `role_id` bigint not null,
  primary key (`id`),
  foreign key (`user_id`) references `user` (`user_id`),
  foreign key (`role_id`) references `role` (`role_id`)
);

insert into `role` values 
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

insert into `user`(user_id, username, password) values 
(1,'admin1','$2a$10$pri5gk9l.tsziw9fxhts8o8mz9e97k2fzbfvgffassituil.tcrfu'),
(2,'user1','$2a$10$pri5gk9l.tsziw9fxhts8o8mz9e97k2fzbfvgffassituil.tcrfu');

insert into `user_role` values 
(1,1,1),
(2,1,2),
(3,2,2);
