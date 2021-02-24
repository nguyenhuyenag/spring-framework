create schema if not exists `english`;
use `english`;
drop table if exists `vocab`;
create table `vocab` (
	`id` int unsigned not null auto_increment,
	`word` varchar(45) not null,
	`pronounce` varchar(45) null,
	`mean` varchar(100) null,
	primary key (`id`, `word`),
	unique index `id_unique` (`id` asc) visible,
	unique index `word_unique` (`word` asc) visible
);
