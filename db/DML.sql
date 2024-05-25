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

-- 테이블 데이터 rcdb.RC_USER_INFO:~1 rows (대략적) 내보내기
INSERT INTO `RC_USER_INFO` (`UID`, `EMAIL`, `PW`, `NAME`, `NICKNAME`, `LEVEL_UID`, `CREATE_AT`, `UPDATE_AT`) VALUES
	('1c95ccb3-fc4d-41ee-8424-e08aba892cca', 'cjw.git@gmail.com', '$2a$10$kVbvt2uaF8QgUuukTGzTiuqxqHbuYCY6ChbGhzSkqbG37MFlCHED2', '최진우', '개발자', 10, '2024-05-25 10:07:08', '2024-05-25 10:07:20');

-- 테이블 데이터 rcdb.RC_USER_LEVEL:~6 rows (대략적) 내보내기
INSERT INTO `RC_USER_LEVEL` (`UID`, `NAME`, `CREATE_AT`, `UPDATE_AT`) VALUES
	(0, '승인 대기자', '2024-05-25 07:23:35', '2024-05-25 07:23:35'),
	(1, '익명 사용자', '2024-05-25 06:08:01', '2024-05-25 06:08:01'),
	(2, '일반 사용자', '2024-05-25 06:08:24', '2024-05-25 06:08:24'),
	(3, '고급 사용자', '2024-05-25 10:17:42', '2024-05-25 10:17:42'),
	(4, '슈퍼 사용자', '2024-05-25 10:24:02', '2024-05-25 10:24:02'),
	(10, '슈퍼 관리자', '2024-05-25 06:08:11', '2024-05-25 06:08:11');

-- 테이블 데이터 rcdb.RC_USER_RESOURCE:~6 rows (대략적) 내보내기
INSERT INTO `RC_USER_RESOURCE` (`UID`, `METHOD`, `PATTERN`, `CREATE_AT`, `UPDATE_AT`) VALUES
	(1, 'ALL', '/**', '2024-05-25 08:34:25', '2024-05-25 10:19:38'),
	(2, 'GET', '/board', '2024-05-25 10:19:54', '2024-05-25 10:31:58'),
	(3, 'POST', '/board', '2024-05-25 10:18:01', '2024-05-25 10:31:59'),
	(4, 'PATCH', '/board', '2024-05-25 10:20:29', '2024-05-25 10:31:59'),
	(5, 'DELETE', '/board', '2024-05-25 10:20:37', '2024-05-25 10:32:00'),
	(6, 'ALL', '/board', '2024-05-25 10:29:10', '2024-05-25 10:32:00');

-- 테이블 데이터 rcdb.RD_ROLE_RESOURCE:~8 rows (대략적) 내보내기
INSERT INTO `RD_ROLE_RESOURCE` (`LEVEL_UID`, `RESOURCE_UID`, `CREATE_AT`, `UPDATE_AT`) VALUES
	(1, 2, '2024-05-25 10:22:17', '2024-05-25 10:22:17'),
	(2, 2, '2024-05-25 10:23:13', '2024-05-25 10:23:13'),
	(2, 3, '2024-05-25 10:22:32', '2024-05-25 10:22:32'),
	(3, 2, '2024-05-25 10:24:30', '2024-05-25 10:24:30'),
	(3, 3, '2024-05-25 10:24:21', '2024-05-25 10:24:21'),
	(3, 4, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(4, 6, '2024-05-25 10:25:21', '2024-05-25 10:31:33'),
	(10, 1, '2024-05-25 08:34:38', '2024-05-25 08:34:38');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
