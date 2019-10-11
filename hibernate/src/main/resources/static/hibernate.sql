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

-- @ManyToOne --
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
