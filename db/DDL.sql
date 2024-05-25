-- --------------------------------------------------------
-- 호스트:                          localhost
-- 서버 버전:                        11.3.2-MariaDB-1:11.3.2+maria~ubu2204 - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- rcdb 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `rcdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `rcdb`;

-- 테이블 rcdb.RC_USER_INFO 구조 내보내기
CREATE TABLE IF NOT EXISTS `RC_USER_INFO` (
  `UID` varchar(36) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `PW` varchar(100) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  `NICKNAME` varchar(50) NOT NULL,
  `LEVEL_UID` bigint(20) NOT NULL DEFAULT 0,
  `CREATE_AT` datetime NOT NULL DEFAULT current_timestamp(),
  `UPDATE_AT` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`UID`) USING BTREE,
  UNIQUE KEY `email` (`EMAIL`) USING BTREE,
  UNIQUE KEY `NICKNAME` (`NICKNAME`),
  KEY `USER_INFO_LEVEL_UID_FK` (`LEVEL_UID`),
  CONSTRAINT `USER_INFO_LEVEL_UID_FK` FOREIGN KEY (`LEVEL_UID`) REFERENCES `RC_USER_LEVEL` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 rcdb.RC_USER_LEVEL 구조 내보내기
CREATE TABLE IF NOT EXISTS `RC_USER_LEVEL` (
  `UID` bigint(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `CREATE_AT` datetime NOT NULL DEFAULT current_timestamp(),
  `UPDATE_AT` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`UID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 rcdb.RC_USER_RESOURCE 구조 내보내기
CREATE TABLE IF NOT EXISTS `RC_USER_RESOURCE` (
  `UID` bigint(20) NOT NULL AUTO_INCREMENT,
  `METHOD` enum('POST','GET','PATCH','DELETE','ALL') NOT NULL,
  `PATTERN` varchar(100) NOT NULL,
  `CREATE_AT` datetime NOT NULL DEFAULT current_timestamp(),
  `UPDATE_AT` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`UID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 rcdb.RD_ROLE_RESOURCE 구조 내보내기
CREATE TABLE IF NOT EXISTS `RD_ROLE_RESOURCE` (
  `LEVEL_UID` bigint(20) NOT NULL,
  `RESOURCE_UID` bigint(20) NOT NULL,
  `CREATE_AT` datetime NOT NULL DEFAULT current_timestamp(),
  `UPDATE_AT` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`LEVEL_UID`,`RESOURCE_UID`),
  KEY `ROLE_RESOURCE_RESOURCE_UID` (`RESOURCE_UID`),
  CONSTRAINT `ROLE_RESOURCE_LEVEL_UID_FK` FOREIGN KEY (`LEVEL_UID`) REFERENCES `RC_USER_LEVEL` (`UID`) ON UPDATE CASCADE,
  CONSTRAINT `ROLE_RESOURCE_RESOURCE_UID` FOREIGN KEY (`RESOURCE_UID`) REFERENCES `RC_USER_RESOURCE` (`UID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
