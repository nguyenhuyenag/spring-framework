use spring_boot_jwt;

CREATE TABLE `roles` (
    `role_id` INT(5) NOT NULL PRIMARY KEY,
    `name` VARCHAR(64) NOT NULL
);
 
CREATE TABLE `user` (
    `id` INT(12) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(64) NOT NULL ,
    `email` VARCHAR (64) NOT NULL ,
    `password` VARCHAR(256),
    `role_id` INT(5),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);
