use spring_boot_jwt;

CREATE TABLE `role` (
    `id` INT(2) NOT NULL PRIMARY KEY,
    `name` VARCHAR(64) NOT NULL
);
 
CREATE TABLE `user` (
    `id` INT(12) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(64) NOT NULL,
    `email` VARCHAR (64) NOT NULL,
    `password` VARCHAR(256)
);

CREATE TABLE `user_role` (
    `user_id` INT(12) NOT NULL,
    `role_id` INT(2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO `spring_boot_jwt`.`role` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `spring_boot_jwt`.`role` (`id`, `name`) VALUES ('2', 'ROLE_USER');
