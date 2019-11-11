use spring_boot_jwt;

CREATE TABLE `user` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(64) NOT NULL,
    `email` VARCHAR (64) NOT NULL,
    `password` VARCHAR(256)
);

CREATE TABLE `role` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(64) NOT NULL
);

CREATE TABLE `user_role` (
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
    FOREIGN KEY (`role_id`) REFERENCES `role`(`id`)
);
