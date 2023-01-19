CREATE DATABASE  IF NOT EXISTS `CRUD_System` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `CRUD_System`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: Ticket_System
-- ------------------------------------------------------
-- Server version	5.6.16


--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL ,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pc` varchar(45) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;


UNLOCK TABLES;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`role_name`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `authorities_ibfk_2` FOREIGN KEY (`role_name`) REFERENCES `role` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `black_toner`;
CREATE TABLE `black_toner` (
  `name` varchar(50) NOT NULL,
  `color` varchar(10) NOT NULL,
  `stock` int(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `magenta_toner`;
CREATE TABLE `magenta_toner` (
  `name` varchar(50) NOT NULL,
  `color` varchar(10) NOT NULL,
  `stock` int(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `cyan_toner`;
CREATE TABLE `cyan_toner` (
  `name` varchar(50) NOT NULL,
  `color` varchar(10) NOT NULL,
  `stock` int(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `yellow_toner`;
CREATE TABLE `yellow_toner` (
  `name` varchar(50) NOT NULL,
  `color` varchar(10) NOT NULL,
  `stock` int(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `black_toner` 
VALUES 
('W2120X','Black','5'),
('TN227BK','Black','4'),
('TN223BK','Black','3');

INSERT INTO `magenta_toner` 
VALUES 
('W2123X','Magenta','5'),
('TN223M','Magenta','4');

INSERT INTO `cyan_toner` 
VALUES 
('W2121X','Cyan','5'),
('TN223C','Cyan','4');

INSERT INTO `yellow_toner` 
VALUES 
('W2122X','Yellow','5'),
('TN223Y','Yellow','4');


DROP TABLE IF EXISTS `printers`;
CREATE TABLE `printers` (
  `name` varchar(50) NOT NULL,
  `manifacturer` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `printers`
VALUES 
('Color LaserJet Enterprise M555dn','HP'),
('HLL3210CW','Brother');


INSERT INTO `role` (name)
VALUES 
('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');

INSERT INTO `users` VALUES 
    ('jdoe','$2y$10$KsdSK1SkKu036eFPoxahBOKOHXQmPrCspdC1egTBmrWeUUL4k6DF6',1,'John','Doe','jdeo@email.com','Pc-001','Male','105'),
    ('rsnow','$2y$10$KsdSK1SkKu036eFPoxahBOKOHXQmPrCspdC1egTBmrWeUUL4k6DF6',1,'Ron','Snow','rshow@email.com','Pc-002','Male','270'),
    ('jadoe','$2y$10$KsdSK1SkKu036eFPoxahBOKOHXQmPrCspdC1egTBmrWeUUL4k6DF6',1,'Jane','Doe','jadoe@email.com','Pc-003','Female','650'),
    ('lsand','$2y$10$KsdSK1SkKu036eFPoxahBOKOHXQmPrCspdC1egTBmrWeUUL4k6DF6',1,'Luisa','Sand','lsand@email.com','Pc-004','Male','422');
    
INSERT INTO `authorities` 
VALUES 
('jadoe','ROLE_EMPLOYEE'),
('lsand','ROLE_MANAGER'),
('jdoe','ROLE_EMPLOYEE'),
('rsnow','ROLE_ADMIN');




DROP TABLE IF EXISTS `printer_Ktoner`;
CREATE TABLE `printer_Ktoner` (
  `name` varchar(50) NOT NULL,
  `black_toner_name` varchar(50) NOT NULL,
  UNIQUE KEY `printers_Ktoner_idx_1` (`black_toner_name`),
  CONSTRAINT `printers_Ktoner_ibfk_1` FOREIGN KEY (`name`) REFERENCES `printers` (`name`),
  CONSTRAINT `printers_Ktoner_ibfk_2` FOREIGN KEY (`black_toner_name`) REFERENCES `black_toner` (`name`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
  DROP TABLE IF EXISTS `printer_Mtoner`;
  CREATE TABLE `printer_Mtoner` (
  `name` varchar(50) NOT NULL,
  `magenta_toner_name` varchar(50) NOT NULL,
  UNIQUE KEY `printers_Mtoner_idx_1` (`magenta_toner_name`),
  CONSTRAINT `printers_Mtoner_ibfk_1` FOREIGN KEY (`name`) REFERENCES `printers` (`name`),
  CONSTRAINT `printers_Mtoner_ibfk_2` FOREIGN KEY (`magenta_toner_name`) REFERENCES `magenta_toner` (`name`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
DROP TABLE IF EXISTS `printer_Ctoner`;
CREATE TABLE `printer_Ctoner` (
  `name` varchar(50) NOT NULL,
  `cyan_toner_name` varchar(50) NOT NULL,
  UNIQUE KEY `printers_Ctoner_idx_1` (`cyan_toner_name`),
  CONSTRAINT `printers_Ctoner_ibfk_1` FOREIGN KEY (`name`) REFERENCES `printers` (`name`),
  CONSTRAINT `printers_Ctoner_ibfk_2` FOREIGN KEY (`cyan_toner_name`) REFERENCES `cyan_toner` (`name`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
  DROP TABLE IF EXISTS `printer_Ytoner`;
  CREATE TABLE `printer_Ytoner` (
  `name` varchar(50) NOT NULL,
  `yellow_toner_name` varchar(50) NOT NULL,
  UNIQUE KEY `printers_Ytoner_idx_1` (`yellow_toner_name`),
  CONSTRAINT `printers_Ytoner_ibfk_1` FOREIGN KEY (`name`) REFERENCES `printers` (`name`),
  CONSTRAINT `printers_Ytoner_ibfk_2` FOREIGN KEY (`yellow_toner_name`) REFERENCES `yellow_toner` (`name`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `printer_Ktoner` 
VALUES 
('Color LaserJet Enterprise M555dn','W2120X'),
('HLL3210CW','TN227BK'),
('HLL3210CW','TN223BK');

INSERT INTO `printer_Mtoner`
VALUES
('Color LaserJet Enterprise M555dn','W2123X'),
('HLL3210CW','TN223M');

INSERT INTO `printer_Ctoner`
VALUES
('Color LaserJet Enterprise M555dn','W2121X'),
('HLL3210CW','TN223C');

INSERT INTO `printer_Ytoner`
VALUES
('Color LaserJet Enterprise M555dn','W2122X'),
('HLL3210CW','TN223Y');
