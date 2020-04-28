-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: bookteca
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `equipo_informatico`
--

DROP TABLE IF EXISTS `equipo_informatico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `equipo_informatico` (
  `id` bigint NOT NULL,
  `disponible` bit(1) DEFAULT NULL,
  `fecha_reserva` date DEFAULT NULL,
  `localizacion` varchar(255) DEFAULT NULL,
  `sistema_operativo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_informatico`
--

LOCK TABLES `equipo_informatico` WRITE;
/*!40000 ALTER TABLE `equipo_informatico` DISABLE KEYS */;
INSERT INTO `equipo_informatico` VALUES (18,_binary '',NULL,'Equipo 45','MacOSX'),(19,_binary '',NULL,'Equipo 32','Linux'),(20,_binary '',NULL,'Equipo 2','Windows'),(21,_binary '',NULL,'Equipo 1','MacOSX'),(53,_binary '',NULL,'Equipo 400','MacOsX');
/*!40000 ALTER TABLE `equipo_informatico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (54),(54),(54),(54),(54);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `libro` (
  `id` bigint NOT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `disponible` bit(1) DEFAULT NULL,
  `editorial` varchar(255) DEFAULT NULL,
  `fec_fin` date DEFAULT NULL,
  `fec_inicio` date DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcsu9wsutearx4pkuyru4ryw5` (`id_usuario_id`),
  CONSTRAINT `FKcsu9wsutearx4pkuyru4ryw5` FOREIGN KEY (`id_usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,'Sergio',_binary '','Anaya',NULL,NULL,'Infantil','Caperucita Roja',NULL),(2,'Borja',_binary '','El Mundo',NULL,NULL,'Infantil','Los Tres Cerditos',NULL),(3,'Dani',_binary '','Santillana',NULL,NULL,'Viajes','La Vuelta Al Mundo En 80 Dias',NULL),(4,'Indef',_binary '\0','España','2020-03-12','2020-03-05','Institucional','Constitucion Española',48),(5,'Cristina',_binary '','AAA',NULL,NULL,'Educativo','Derecho Mercantil Español',NULL),(6,'Sergio',_binary '','Anaya',NULL,NULL,'Infantil','Caperucita Roja',NULL),(7,'Sergio',_binary '','Anaya',NULL,NULL,'Infantil','Caperucita Roja',NULL),(8,'Dani',_binary '','Santillana',NULL,NULL,'Viajes','La Vuelta Al Mundo En 80 Dias',NULL),(50,'Ministerio Salud',_binary '','URJC',NULL,NULL,'Medicina','Medicina General',NULL);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revista`
--

DROP TABLE IF EXISTS `revista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `revista` (
  `id` bigint NOT NULL,
  `disponible` bit(1) DEFAULT NULL,
  `editorial` varchar(255) DEFAULT NULL,
  `fasciculo` int DEFAULT NULL,
  `fec_fin` date DEFAULT NULL,
  `fec_inicio` date DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh6amvaqp5258yrge6ucjq52xv` (`id_usuario_id`),
  CONSTRAINT `FKh6amvaqp5258yrge6ucjq52xv` FOREIGN KEY (`id_usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revista`
--

LOCK TABLES `revista` WRITE;
/*!40000 ALTER TABLE `revista` DISABLE KEYS */;
INSERT INTO `revista` VALUES (9,_binary '','Editorial GQ',123,NULL,NULL,'Actualidad','GQ',NULL),(10,_binary '','Editorial FHM',35,NULL,NULL,'Erotica','FHM',NULL),(11,_binary '','Editorial MasQueCoches',77,NULL,NULL,'Automovil','MasQueCoches',NULL),(12,_binary '','Editorial AutoSport',23,NULL,NULL,'Automovil','AutoSport',NULL),(13,_binary '','Editorial MuyInteresante',456,NULL,NULL,'Intelectual','MuyInteresante',NULL),(51,_binary '','Editorial Marca',5674,NULL,NULL,'Deportivo','Marca',NULL);
/*!40000 ALTER TABLE `revista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala_trabajo_grupo`
--

DROP TABLE IF EXISTS `sala_trabajo_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sala_trabajo_grupo` (
  `id` bigint NOT NULL,
  `capacidad` int DEFAULT NULL,
  `compartida` bit(1) DEFAULT NULL,
  `disponible` bit(1) DEFAULT NULL,
  `fecha_reserva` date DEFAULT NULL,
  `localizacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala_trabajo_grupo`
--

LOCK TABLES `sala_trabajo_grupo` WRITE;
/*!40000 ALTER TABLE `sala_trabajo_grupo` DISABLE KEYS */;
INSERT INTO `sala_trabajo_grupo` VALUES (14,16,_binary '',_binary '\0','2020-03-05','Planta 1'),(15,3,_binary '\0',_binary '',NULL,'Planta 0'),(16,10,_binary '',_binary '',NULL,'Planta 2'),(17,5,_binary '\0',_binary '',NULL,'Planta 0'),(52,4,_binary '\0',_binary '',NULL,'Planta 3');
/*!40000 ALTER TABLE `sala_trabajo_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL,
  `max` int NOT NULL,
  `administrador` bit(1) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `contrasenya` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `puesto_informatico_id` bigint DEFAULT NULL,
  `sala_trabajo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7bhnr5jb9kyn2enjye393xkgp` (`puesto_informatico_id`),
  KEY `FKibg71nakerppkxjp86a28cdw5` (`sala_trabajo_id`),
  CONSTRAINT `FK7bhnr5jb9kyn2enjye393xkgp` FOREIGN KEY (`puesto_informatico_id`) REFERENCES `equipo_informatico` (`id`),
  CONSTRAINT `FKibg71nakerppkxjp86a28cdw5` FOREIGN KEY (`sala_trabajo_id`) REFERENCES `sala_trabajo_grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (43,3,_binary '\0','Martin Alonso','$2a$10$2ZQeoUW7kq1TiLYlM4CoKO1.HdyLKNcJ5T3Bx2ZJof8JXmIWGynaG','bormaral13@gmail.com','Borja',NULL,NULL),(44,3,_binary '','Hernandez Dominguez','$2a$10$GMA0WWcY/5YXHw7iFw3AxuJmbydif9BncCTE5YYaRh6XJip1VOgPq','sergiohd47@gmail.com','Sergio',NULL,NULL),(45,3,_binary '\0','Molina Ballesteros','$2a$10$/YgAoLKPQaMpeyd9RP6IR.NvOa8IvWli8rXkj9aY7MqDDxqcZwqpu','dmolinaballesteros@gmail.com','Daniel',NULL,NULL),(46,3,_binary '\0','Picapleitos','$2a$10$f2Kp6hbUGqc27cYwLAuz.uk9lpcjAEg3ukm2npjQxwXZmwjQyp1uq','borrego90@gmail.com','Pablo',NULL,NULL),(47,3,_binary '','Diaz','$2a$10$iJO2qbxb0.i.lIr.KioMwuwsukOMm9h/.m4PRg5vv.mAN46skaTK6','adiaz@gmail.com','Alvaro',NULL,NULL),(48,3,_binary '\0','Prueba','$2a$10$BchaLaqweDdEidpyRBZxe.6qKcRYxBIk0sSi7YPpbie3kCGwL.ejK','pep@gmail.com','Pepe',NULL,14),(49,3,_binary '','Muñoz','$2a$10$ix8KoRGTfUZ.OvJsmrl5Lus6QwL1daCE9QWq0INPocwOuOrUuI6hm','nick@gmail.com','Nicolas',NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-06 11:46:20
