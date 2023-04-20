CREATE DATABASE  IF NOT EXISTS `demo_database`;
USE `demo_database`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(45) DEFAULT NULL,
  `employeeRole` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `employee` VALUES 
    (1,'attyuttam','ADMIN'),
    (2,'debarka','DEVELOPER'),
    (3,'nilesh','MANAGER');
	
CREATE DATABASE  IF NOT EXISTS `demo_database_2`;
USE `demo_database_2`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(45) DEFAULT NULL,
  `employeeRole` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `employee` VALUES 
    (1,'sraddha','LEAD');