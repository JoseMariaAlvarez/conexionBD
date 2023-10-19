CREATE DATABASE  IF NOT EXISTS `pruebaLigadb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pruebaLigadb`;
-- MySQL dump 10.13  Distrib 8.0.25, for macos11 (x86_64)
--
-- Host: introingsoft2021.cobadwnzalab.eu-central-1.rds.amazonaws.com    Database: pruebaLigadb
-- ------------------------------------------------------
-- Server version	8.0.20

--
-- GTID state at the beginning of the backup 
--

--
-- Table structure for table `EQUIPO`
--

DROP TABLE IF EXISTS `EQUIPO`;
CREATE TABLE `EQUIPO` (
  `identificador` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  UNIQUE KEY `identificador_UNIQUE` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `EQUIPO`
--

LOCK TABLES `EQUIPO` WRITE;

INSERT INTO `EQUIPO` VALUES (1,'La Unidad'),(2,'Malaka'),(3,'Carlinda'),(4,'Olímpica Victoriana');

UNLOCK TABLES;

--
-- Table structure for table `JUGADOR`
--

DROP TABLE IF EXISTS `JUGADOR`;

CREATE TABLE `JUGADOR` (
  `identificador` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `idEquipo` int DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  UNIQUE KEY `identificador_UNIQUE` (`identificador`),
  KEY `idEquipo_idx` (`idEquipo`),
  CONSTRAINT `idEquipo` FOREIGN KEY (`idEquipo`) REFERENCES `EQUIPO` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `JUGADOR`
--

LOCK TABLES `JUGADOR` WRITE;

INSERT INTO `JUGADOR` VALUES (1,'Héctor',19,1),(2,'Pablo',19,1),(3,'Carlos',18,2),(4,'Sergio',20,3);

UNLOCK TABLES;

-- Dump completed on 2021-05-12 10:03:02
