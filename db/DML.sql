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

-- 테이블 데이터 rcdb.RC_BOARD:~1 rows (대략적) 내보내기
INSERT INTO `RC_BOARD` (`UID`, `BBS_UID`, `TITLE`, `CONTENTS`, `WRITER_UID`, `HIT`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(15, 1, '공지사항 1입니다', '잘부탁드려요', '1c95ccb3-fc4d-41ee-8424-e08aba892cca', 0, '2024-05-27 17:52:47', '2024-05-27 17:52:47');

-- 테이블 데이터 rcdb.RC_BOARD_BBS:~3 rows (대략적) 내보내기
INSERT INTO `RC_BOARD_BBS` (`UID`, `PATH`, `TITLE`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(1, 'notice', '공지사항', '2024-05-26 03:50:57', '2024-05-26 03:51:46'),
	(2, 'free', '자유게시판', '2024-05-26 03:51:02', '2024-05-26 03:51:59'),
	(3, 'qna', 'QnA', '2024-05-26 03:51:09', '2024-05-26 03:51:52');

-- 테이블 데이터 rcdb.RC_BOARD_COMMENT:~1 rows (대략적) 내보내기
INSERT INTO `RC_BOARD_COMMENT` (`UID`, `BOARD_UID`, `WRITER_UID`, `CONTENTS`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(3, 15, '1c95ccb3-fc4d-41ee-8424-e08aba892cca', '하이요~', '2024-05-28 12:34:05', '2024-05-28 12:34:05');

-- 테이블 데이터 rcdb.RC_FILE:~0 rows (대략적) 내보내기

-- 테이블 데이터 rcdb.RC_USER:~2 rows (대략적) 내보내기
INSERT INTO `RC_USER` (`UID`, `EMAIL`, `PW`, `NAME`, `NICKNAME`, `LEVEL_UID`, `CREATED_AT`, `UPDATED_AT`) VALUES
	('10b013fa-59f1-41b1-9e90-8d28337cd86f', 'test@test.com', '$2a$10$K7mKXXGqpc9bqgi4OOAK1eJhn.SOasYyKfbBDXI0l0i3ZD6hIXof2', '테스트', '테스트', 1, '2024-05-31 13:38:03', '2024-05-31 13:38:13'),
	('1c95ccb3-fc4d-41ee-8424-e08aba892cca', 'cjw.git@gmail.com', '$2a$10$kVbvt2uaF8QgUuukTGzTiuqxqHbuYCY6ChbGhzSkqbG37MFlCHED2', '최진우', '개발자', 10, '2024-05-25 10:07:08', '2024-05-26 04:43:57');

-- 테이블 데이터 rcdb.RC_USER_LEVEL:~5 rows (대략적) 내보내기
INSERT INTO `RC_USER_LEVEL` (`UID`, `NAME`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(0, '승인 대기자', '2024-05-25 07:23:35', '2024-05-25 07:23:35'),
	(1, '익명 사용자', '2024-05-25 06:08:01', '2024-05-25 06:08:01'),
	(2, '일반 사용자', '2024-05-25 06:08:24', '2024-05-25 06:08:24'),
	(3, '고급 사용자', '2024-05-25 10:17:42', '2024-05-25 10:17:42'),
	(4, '슈퍼 사용자', '2024-05-25 10:24:02', '2024-05-25 10:24:02'),
	(10, '슈퍼 관리자', '2024-05-25 06:08:11', '2024-05-25 06:08:11');

-- 테이블 데이터 rcdb.RC_USER_RESOURCE:~12 rows (대략적) 내보내기
INSERT INTO `RC_USER_RESOURCE` (`UID`, `METHOD`, `PATTERN`, `DESCRIPTION`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(1, 'ALL', '/**', '모든 권한', '2024-05-25 08:34:25', '2024-05-31 13:49:30'),
	(2, 'GET', '/board/*', '게시판 목록 보기', '2024-05-25 10:19:54', '2024-05-31 13:56:49'),
	(3, 'GET', '/board/*/*', '게시판 게시글 상세보기', '2024-05-26 03:30:17', '2024-05-31 13:56:53'),
	(4, 'POST', '/board/*', '게시판 게시글 등록', '2024-05-25 10:18:01', '2024-05-31 13:56:57'),
	(5, 'PATCH', '/board/*', '게시판 게시글 수정', '2024-05-25 10:20:29', '2024-05-31 13:57:00'),
	(6, 'DELETE', '/board/*/*', '게시판 게시글 삭제', '2024-05-25 10:20:37', '2024-05-31 13:57:03'),
	(7, 'ALL', '/board/**', '게시판 게시글 모든 권한', '2024-05-25 10:29:10', '2024-05-31 13:57:08'),
	(8, 'GET', '/comment/*', '게시글 댓글 목록', '2024-05-27 15:51:44', '2024-05-31 13:57:14'),
	(9, 'POST', '/comment', '게시글 댓글 등록', '2024-05-27 15:52:36', '2024-05-31 13:57:18'),
	(10, 'PATCH', '/comment', '게시글 댓글 수정', '2024-05-27 15:52:49', '2024-05-31 13:57:21'),
	(11, 'DELETE', '/comment/*', '게시글 댓글 삭제', '2024-05-27 15:53:23', '2024-05-31 13:57:25'),
	(12, 'ALL', '/comment/**', '게시글 댓글 모든 권한', '2024-05-27 16:03:07', '2024-05-31 13:57:28');

-- 테이블 데이터 rcdb.RD_ROLE_RESOURCE:~10 rows (대략적) 내보내기
INSERT INTO `RD_ROLE_RESOURCE` (`LEVEL_UID`, `RESOURCE_UID`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(1, 2, '2024-05-25 10:22:17', '2024-05-25 10:22:17'),
	(2, 2, '2024-05-25 10:23:13', '2024-05-25 10:23:13'),
	(2, 3, '2024-05-26 03:34:17', '2024-05-26 03:34:17'),
	(2, 4, '2024-05-25 10:22:32', '2024-05-25 10:22:32'),
	(3, 2, '2024-05-25 10:24:30', '2024-05-25 10:24:30'),
	(3, 3, '2024-05-26 03:34:30', '2024-05-26 03:34:30'),
	(3, 4, '2024-05-25 10:24:21', '2024-05-25 10:24:21'),
	(3, 5, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(4, 7, '2024-05-25 10:25:21', '2024-05-25 10:31:33'),
	(10, 1, '2024-05-25 08:34:38', '2024-05-25 08:34:38');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
