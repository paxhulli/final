CREATE DATABASE  IF NOT EXISTS `gers_garage` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gers_garage`;
-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (arm64)
--
-- Host: localhost    Database: gers_garage
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id_clients` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `mobile_number` varchar(45) NOT NULL,
  `email` varchar(65) NOT NULL,
  `password` varchar(70) NOT NULL,
  `license` varchar(45) NOT NULL,
  `profile` varchar(120) NOT NULL,
  PRIMARY KEY (`id_clients`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Jesus','Garcia','5556789543','admin@admin.com','$2a$12$sHnci084GhXbk7KLKAnre.8MAo7Tazng4SCuWeUMXY12eCJfqP7x2','mn345','ROLE_ADMIN'),(2,'Karen','Flores','5510114524','client','$2a$12$sHnci084GhXbk7KLKAnre.8MAo7Tazng4SCuWeUMXY12eCJfqP7x2','ki283','ROLE_USER'),(3,'Juan','Mecánico','334343434','meca@meca.com','$2a$12$sHnci084GhXbk7KLKAnre.8MAo7Tazng4SCuWeUMXY12eCJfqP7x2','HD/&&/','ROLE_STAFF'),(4,'Jesus','Her','33333333','jesus@jesus.com','1234','rrr555','ROLE_USER'),(5,'Juan','Garcia','555555','juan@juan.com','$2a$10$wNGqGrOY5XmXxPNXCBz7W.uqsmXQc0incG1xy3Cn3G1EpFICUb7Ze','jn66','ROLE_USER'),(6,'pepe','pepe','12324324','pepe@pepe.com','$2a$10$9/vC1qTrcLplWnrd5eXXGOw/FmZbR9Ocdet1SPIe2d6/M3vMjJeGS','LCNC123213','ROLE_USER'),(7,'santi','santi','33344','santi@santi.com','$2a$10$4lttdoe/RZZftJglA/OC.uDT5G3JPCQ/yeXOcAQo90/qiF8dpKGzu','kkkk434','ROLE_USER'),(8,'a','a','8989','a@a.com','$2a$10$vWBoV.3sfTHyAauxXKmQM.1Adj0z6qiVsQLLz.eQn2n1CeWvWXmfu','eeee','ROLE_USER'),(9,'b','b','99','b@b.com','$2a$10$l6hU/BfYvNsduylhVvssR.c1r/s8I8t3sJJHfpbciM9N5p4UR6fJy','dede3456','ROLE_USER'),(10,'c','c','9879879','c@c.com','$2a$10$L3pzlC0vlWCsLLpG6oNkyuCwaW.zC6SempSd.gL5uLDVbrrgfBzQm','ddd','ROLE_USER'),(11,'d','d','222','d@d.com','$2a$10$bCBLQDOkMk4/PZaK2zS76./ZJEG2c23Bx5BtIrgV6HvpsRn0vMspe','ddd','ROLE_USER');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `engine_type`
--

DROP TABLE IF EXISTS `engine_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `engine_type` (
  `id_engine_type` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id_engine_type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `engine_type`
--

LOCK TABLES `engine_type` WRITE;
/*!40000 ALTER TABLE `engine_type` DISABLE KEYS */;
INSERT INTO `engine_type` VALUES (1,'Petrol','Petrol combustion engine.'),(2,'Diesel','Diesel combustion engine.'),(3,'Electric','Electric motor powered.'),(4,'Hybrid','More than one means of propulsion.');
/*!40000 ALTER TABLE `engine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `has_vehicle`
--

DROP TABLE IF EXISTS `has_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `has_vehicle` (
  `vehicles_registration_number` varchar(20) NOT NULL,
  `clients_id_clients` int NOT NULL,
  PRIMARY KEY (`vehicles_registration_number`,`clients_id_clients`),
  KEY `fk_has_vehicle_vehicles1_idx` (`vehicles_registration_number`),
  KEY `fk_has_vehicle_clients1_idx` (`clients_id_clients`),
  CONSTRAINT `fk_has_vehicle_clients1` FOREIGN KEY (`clients_id_clients`) REFERENCES `clients` (`id_clients`),
  CONSTRAINT `fk_has_vehicle_vehicles1` FOREIGN KEY (`vehicles_registration_number`) REFERENCES `vehicles` (`registration_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `has_vehicle`
--

LOCK TABLES `has_vehicle` WRITE;
/*!40000 ALTER TABLE `has_vehicle` DISABLE KEYS */;
INSERT INTO `has_vehicle` VALUES ('CAD555',2),('DDD333',2),('lllll999',2),('REG003',2),('REG004',2),('REG005',2),('REG009',2),('TRERR',2),('REG010',5),('cccccc',10),('dffrfrf',10),('tytyty',10);
/*!40000 ALTER TABLE `has_vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_parts`
--

DROP TABLE IF EXISTS `items_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_parts` (
  `id_items_parts` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` decimal(4,0) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id_items_parts`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_parts`
--

LOCK TABLES `items_parts` WRITE;
/*!40000 ALTER TABLE `items_parts` DISABLE KEYS */;
INSERT INTO `items_parts` VALUES (1,'Wheel2222',502,'Lorem ipsum dolor sit amet.222',20),(2,'Window',40,'Lorem ipsum dolor sit amet.',8),(3,'Light',55,'Lorem ipsum dolor sit amet.',12),(4,'Seat belt ee',102,'Lorem ipsum dolor sit amet.',4),(5,'Seat',100,'Lorem ipsum dolor sit amet.',4),(6,'Windshield',100,'Lorem ipsum dolor sit amet.',6),(7,'Windshield wiper',30,'Lorem ipsum dolor sit amet.',8),(8,'Rearview',40,'Lorem ipsum dolor sit amet.',3),(9,'Stoppers',50,'Lorem ipsum dolor sit amet.',7),(10,'Rims',10,'Lorem ipsum dolor sit amet.',15),(11,'Chassis',800,'Lorem ipsum dolor sit amet.',2),(12,'Engine',700,'Lorem ipsum dolor sit amet.',2),(13,'Transmission',100,'Lorem ipsum dolor sit amet.',5),(14,'Battery',50,'Lorem ipsum dolor sit amet.',2),(15,'Alternator',50,'Lorem ipsum dolor sit amet.',7),(16,'Radiador',70,'Lorem ipsum dolor sit amet.',3),(17,'Axle',50,'Lorem ipsum dolor sit amet.',5),(18,'Suspension',80,'Lorem ipsum dolor sit amet.',2),(19,'Steering System',75,'Lorem ipsum dolor sit amet.',6),(20,'Brakes',80,'Lorem ipsum dolor sit amet.',2),(21,'Shock Absorbers',120,'Lorem ipsum dolor sit amet.',5),(22,'Catalytic Converter',80,'Lorem ipsum dolor sit amet.',2),(23,'Muffler',50,'Lorem ipsum dolor sit amet.',9),(24,'AC Compressor',80,'Lorem ipsum dolor sit amet.',4),(25,'Serpentine belt',30,'Lorem ipsum dolor sit amet.',7),(26,'Tailpipe',70,'Lorem ipsum dolor sit amet.',3),(27,'Fuel Tank',130,'Lorem ipsum dolor sit amet.',8),(28,'Speedometer',90,'Lorem ipsum dolor sit amet.',2),(29,'Headlights',75,'Lorem ipsum dolor sit amet.',7),(30,'Hood',80,'Lorem ipsum dolor sit amet.',2),(31,'Fuel Gauge',150,'Lorem ipsum dolor sit amet.',6),(32,'Temperature Gauge',20,'Lorem ipsum dolor sit amet.',3),(33,'Car Trip Meter',40,'Lorem ipsum dolor sit amet.',6),(34,'Rev Counter',30,'Lorem ipsum dolor sit amet.',8),(35,'Cylinder Block',60,'Lorem ipsum dolor sit amet.',3),(36,'Cylinder Head',120,'Lorem ipsum dolor sit amet.',4),(37,'Pistons',70,'Lorem ipsum dolor sit amet.',6),(38,'Crankshaft',45,'Lorem ipsum dolor sit amet.',2),(39,'Clutch',80,'Lorem ipsum dolor sit amet.',6),(40,'Gearbox',90,'Lorem ipsum dolor sit amet.',3),(41,'Amortiguador',890,'Amortiguador Bogue',100),(42,'Wheel',55,'Lorem ipsum dolor sit amet.',20),(43,'Wheel',54,'Lorem ipsum dolor sit amet.',20);
/*!40000 ALTER TABLE `items_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `make`
--

DROP TABLE IF EXISTS `make`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `make` (
  `id_make` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`id_make`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `make`
--

LOCK TABLES `make` WRITE;
/*!40000 ALTER TABLE `make` DISABLE KEYS */;
INSERT INTO `make` VALUES (1,'Abarth','Italy'),(2,'Aston Martin','England'),(3,'Audi','Germany'),(4,'Alfa Romeo','Italy'),(5,'BMW','Germany'),(6,'Cadillac','America'),(7,'Bentley','England'),(8,'Chevrolet','America'),(9,'Chrysler','America'),(10,'Citroen','France'),(11,'Daewoo','South Korea'),(12,'Datsun','Japan'),(13,'Dodge','America'),(14,'Ferrari','Italy'),(15,'Fiat','Italy'),(16,'Ford','America'),(17,'Great Wall','China'),(18,'Haval','China'),(19,'Honda','Japan'),(20,'Hyundai','South Korea'),(21,'Infiniti','Japan'),(22,'Jaguar','England'),(23,'Jeep','America'),(24,'Kia','South Korea'),(25,'Mazda','Japan'),(26,'Mini','England'),(27,'Nissan','Japan'),(28,'Renault','France'),(29,'Smart','Germany'),(30,'Volkswagen','Germany');
/*!40000 ALTER TABLE `make` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `needs`
--

DROP TABLE IF EXISTS `needs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `needs` (
  `id_needs` int NOT NULL AUTO_INCREMENT,
  `service_id_service` int NOT NULL,
  `items_parts_id_items_parts` int NOT NULL,
  PRIMARY KEY (`id_needs`,`service_id_service`,`items_parts_id_items_parts`),
  KEY `fk_needs_service1_idx` (`service_id_service`),
  KEY `fk_needs_items_parts1_idx` (`items_parts_id_items_parts`),
  CONSTRAINT `fk_needs_items_parts1` FOREIGN KEY (`items_parts_id_items_parts`) REFERENCES `items_parts` (`id_items_parts`),
  CONSTRAINT `fk_needs_service1` FOREIGN KEY (`service_id_service`) REFERENCES `service` (`id_service`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `needs`
--

LOCK TABLES `needs` WRITE;
/*!40000 ALTER TABLE `needs` DISABLE KEYS */;
/*!40000 ALTER TABLE `needs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id_service` int NOT NULL AUTO_INCREMENT,
  `service_type_id_service_type` int NOT NULL,
  `vehicles_registration_number` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `staff_id_staff` int NOT NULL,
  `status_id_status` int NOT NULL,
  `extra_notes` varchar(205) NOT NULL,
  `time` int NOT NULL,
  PRIMARY KEY (`id_service`,`status_id_status`),
  KEY `fk_service_service_type1_idx` (`service_type_id_service_type`),
  KEY `fk_service_vehicles1_idx` (`vehicles_registration_number`),
  KEY `fk_service_staff1_idx` (`staff_id_staff`),
  KEY `fk_service_status1_idx` (`status_id_status`),
  CONSTRAINT `fk_service_service_type1` FOREIGN KEY (`service_type_id_service_type`) REFERENCES `service_type` (`id_service_type`),
  CONSTRAINT `fk_service_staff1` FOREIGN KEY (`staff_id_staff`) REFERENCES `staff` (`id_staff`),
  CONSTRAINT `fk_service_status1` FOREIGN KEY (`status_id_status`) REFERENCES `status` (`id_status`),
  CONSTRAINT `fk_service_vehicles1` FOREIGN KEY (`vehicles_registration_number`) REFERENCES `vehicles` (`registration_number`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (3,1,'CAD555','2023-07-05 00:00:00',1,1,'tttttt',9),(4,1,'REG004','2023-08-04 00:00:00',0,1,'fffffggggggg',11),(5,1,'CAD555','2023-08-09 00:00:00',0,1,'qewqeqwewqe',9),(6,1,'REG005','2023-08-21 00:00:00',0,1,'ggggggg',13),(7,3,'lllll999','2023-08-20 00:00:00',0,1,'qwrrrrr',9),(8,2,'CAD555','2023-08-20 00:00:00',0,1,'ddddd',10),(9,3,'lllll999','2023-08-12 00:00:00',0,1,'ddddd',10),(10,2,'DDD333','2023-08-01 00:00:00',0,1,'hyhyh',10),(11,2,'CAD555','2023-08-31 00:00:00',0,1,'hh',9),(12,3,'DDD333','2023-08-08 00:00:00',0,1,'gtgt',9),(13,2,'lllll999','2023-08-01 00:00:00',0,1,'hyhy',9),(14,1,'DDD333','2023-08-12 00:00:00',0,1,'ffff',10),(15,1,'CAD555','2023-08-20 00:00:00',0,1,'f',9),(16,2,'CAD555','2023-09-02 00:00:00',0,1,'TTTT',9),(17,1,'REG009','2023-08-14 00:00:00',0,1,'test login',9),(18,1,'cccccc','2023-08-13 00:00:00',0,1,'wwww',9),(19,4,'dffrfrf','2023-08-11 00:00:00',0,1,'madñ,asmdkñsad akldnaslk dm lksjd lkajdlkasjd jadlkjasdklja dlkjkljasdklja dklja dlkjadljasd',11);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_type`
--

DROP TABLE IF EXISTS `service_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_type` (
  `id_service_type` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `price` decimal(4,0) NOT NULL DEFAULT '200',
  PRIMARY KEY (`id_service_type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_type`
--

LOCK TABLES `service_type` WRITE;
/*!40000 ALTER TABLE `service_type` DISABLE KEYS */;
INSERT INTO `service_type` VALUES (1,'Annual Service','Routine check up.',200),(2,'Major Service','Annual service plus extras.',280),(3,'Repair/Fault','Restore functionality.',180),(4,'Major Repair','Restore complex functionality.',300);
/*!40000 ALTER TABLE `service_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id_staff` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `mobile_number` varchar(45) NOT NULL,
  PRIMARY KEY (`id_staff`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (0,'NA','NA','0000'),(1,'John','O\'Connell','0853593456'),(2,'Cian','Murphy','0830982345'),(3,'Ross','O\'Neills','0867896754'),(4,'William','Higgins','0893450987');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id_status` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Booked','The vehicle has been booked.'),(2,'In Service','The vehicle is been repaired.'),(3,'Fixed','The vehicle has been fixed.'),(4,'Collected','Client already took the car away.'),(5,'Irreparable/Scrapped','No longer useful.');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `registration_number` varchar(20) NOT NULL,
  `model` varchar(45) NOT NULL,
  `year` int NOT NULL,
  `make_id_make` int NOT NULL,
  `engine_type_id_engine_type` int NOT NULL,
  PRIMARY KEY (`registration_number`),
  UNIQUE KEY `registration_number_UNIQUE` (`registration_number`),
  KEY `fk_vehicles_make_idx` (`make_id_make`),
  KEY `fk_vehicles_engine_type1_idx` (`engine_type_id_engine_type`),
  CONSTRAINT `fk_vehicles_engine_type1` FOREIGN KEY (`engine_type_id_engine_type`) REFERENCES `engine_type` (`id_engine_type`),
  CONSTRAINT `fk_vehicles_make` FOREIGN KEY (`make_id_make`) REFERENCES `make` (`id_make`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES ('CAD555','CD34',2000,6,1),('cccccc','Cityy',1998,2,3),('DDD333','Jaguar one',2020,22,4),('DER222','Jaguar one',2020,22,1),('dffrfrf','eee',2023,1,1),('lllll999','wwwwff',2020,2,2),('REG001','Fiesta',2012,16,1),('REG002','Chevy',2000,8,1),('REG003','Caliver',1999,13,4),('REG004','Fiessta',11989,16,3),('REG005','A10',1999,5,2),('REG009','VEnto',1999,4,1),('REG010','Ventura',2019,23,1),('RGF3435','City',2020,19,2),('RGF3436','Civico',2020,19,2),('TRERR','DTN5454',1988,12,1),('tytyty','www',1998,3,4),('WER123','city',2022,19,1);
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-10 17:37:59
