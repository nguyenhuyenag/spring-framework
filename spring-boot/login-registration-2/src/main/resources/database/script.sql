CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `username` (`username`) USING BTREE,
  KEY `password` (`password`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
