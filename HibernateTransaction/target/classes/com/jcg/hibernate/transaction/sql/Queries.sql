CREATE DATABASE IF NOT EXISTS  tutorialDb;

USE tutorialDb;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id int(11), 
	user_name varchar(15) DEFAULT NULL, 
	created_by varchar(100) DEFAULT NULL, 
	created_date DATE DEFAULT NULL, 
	PRIMARY KEY (user_id)
);

DESC users;