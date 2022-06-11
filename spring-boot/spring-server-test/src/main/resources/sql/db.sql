CREATE TABLE IF NOT EXISTS user (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  create_time datetime DEFAULT current_timestamp,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS role (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_id int NOT NULL,
  role_id int NOT NULL,
  PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS jokes (
	id BIGINT NOT NULL,
	type VARCHAR(45) NULL, 
	categories VARCHAR(45) NULL,
	joke LONGTEXT NULL, 
	create_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

INSERT INTO role VALUES (1, 'ADMIN', 'Admin role'), (2, 'USER', 'User role');
