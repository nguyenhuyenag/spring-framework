CREATE SCHEMA `multitenant1`;
USE multitenant1;
CREATE TABLE `multitenant1`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

CREATE SCHEMA `multitenant2`;
USE multitenant2;
CREATE TABLE `multitenant2`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);
