CREATE TABLE `app_user` (
  `USER_ID` bigint NOT NULL,
  `USER_NAME` varchar(36) NOT NULL,
  `ENCRYTED_PASSWORD` varchar(128) NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `APP_USER_UK` (`USER_NAME`)
);

CREATE TABLE `app_role` (
  `ROLE_ID` bigint NOT NULL,
  `ROLE_NAME` varchar(30) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `APP_ROLE_UK` (`ROLE_NAME`)
);

CREATE TABLE `user_role` (
  `ID` bigint NOT NULL,
  `USER_ID` bigint NOT NULL,
  `ROLE_ID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ROLE_UK` (`USER_ID`,`ROLE_ID`),
  KEY `USER_ROLE_FK2` (`ROLE_ID`),
  CONSTRAINT `USER_ROLE_FK1` FOREIGN KEY (`USER_ID`) REFERENCES `app_user` (`USER_ID`),
  CONSTRAINT `USER_ROLE_FK2` FOREIGN KEY (`ROLE_ID`) REFERENCES `app_role` (`ROLE_ID`)
);

INSERT INTO `user_role` VALUES 
(1,1,1),
(2,1,2),
(3,2,2);

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
);

INSERT INTO `app_role` VALUES 
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

INSERT INTO `app_user` VALUES 
(1,'dbadmin1','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu'),
(2,'dbuser1','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');
