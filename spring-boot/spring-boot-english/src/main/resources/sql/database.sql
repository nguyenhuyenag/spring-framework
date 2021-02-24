CREATE SCHEMA `english`;
USE `english`;
CREATE TABLE `dictionary` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`word` VARCHAR(45) NOT NULL,
	`pronounce` VARCHAR(45) NULL,
	`mean` VARCHAR(100) NULL,
	PRIMARY KEY (`id`, `word`),
	UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
	UNIQUE INDEX `word_UNIQUE` (`word` ASC) VISIBLE
);