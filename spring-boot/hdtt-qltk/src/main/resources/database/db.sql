
CREATE TABLE `ixhd_user` (
  `id` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'token_pwd',
  `mst_tcgp` varchar(15) DEFAULT NULL COMMENT 'MST_TCGP',
  `full_name` varchar(255) DEFAULT NULL,
  `skey` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0' COMMENT '0: deactive; 1: active',
  PRIMARY KEY (`id`),
  UNIQUE KEY `1` (`email`)
);

INSERT INTO `ixhd_user` VALUES ('0001', 'test@ts24.com.vn', '$2a$10$EhY5P8NCuFiSp8KIAIbaseX/5OSDKMQQGOmmUwoxoKsngioXyaw0G', '123456789', 'DỊCH VỤ IXHD TS24', 'GnutWK8ygafMg03DT4ksuSejlVENuF0c', '1');
INSERT INTO `ixhd_user` VALUES ('0003', 'test_api@ts24.com.vn', '$2a$10$xjBnI.WGkhM.CpQMHqnpbu.rrgCZWCCnp0f2dzxpDjdOD4Btw48D2', '123456789', 'DEMO TEST API_TCTN', 'GnutWK8ygafMg03DT4ksuSejlVENuF0c', '1');
INSERT INTO `ixhd_user` VALUES ('0004', 'abc@ts24.com.vn', '$2a$10$SEXHMyTixU6WMpEjd2YRqexXGtjeohEEmfhlgt361OzG4ZijJKo0a', '123456789', 'DEMO - TCGP CUNG CẤP DỊCH VỤ HDDT', 'GnutWK8ygafMg03DT4ksuSejlVENuF0c', '1');
