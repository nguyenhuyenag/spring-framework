CREATE SCHEMA IF NOT EXISTS `spring_hibernate`;

USE `spring_hibernate`;

CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `code` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`, `code`)
);

CREATE TABLE `mysql_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `Age` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `camelCase` varchar(45) DEFAULT NULL,
  `card_number` varchar(45) DEFAULT NULL,
  `language_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

---  @OneToOne  ---
CREATE TABLE `my_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255),
  `address_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE `my_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(255),
  `city` varchar(255),
  PRIMARY KEY (`id`) USING BTREE
);

---  @ManyToOne  ---
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`company_id`) REFERENCES `company`(`id`)
);

---  @GenericGenerator  ---
CREATE TABLE `employee` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `empl` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `empl_position` (
  `empl_id` INT(11) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`empl_id`, `position`)
);

/*--- many to many ---*/
CREATE TABLE `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `product_category` (
  `product_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  FOREIGN KEY (`product_id`) REFERENCES `product`(`id`)
);
/*--- end many to many ---*/

CREATE TABLE `country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);
  
CREATE TABLE `province` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country_id` INT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
);

CREATE TABLE `programing` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `position` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  UNIQUE (id, email)
);
