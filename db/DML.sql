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

-- 테이블 데이터 rcdb.RC_BOARD:~2 rows (대략적) 내보내기
INSERT INTO `RC_BOARD` (`UID`, `BBS_UID`, `TITLE`, `CONTENTS`, `WRITER_UID`, `HIT`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(35, 1, '첨부파일 Flux 테스트예여', 'ㅈㄱㄴ', '1c95ccb3-fc4d-41ee-8424-e08aba892cca', 0, '2024-06-05 17:29:20', '2024-06-05 17:29:20'),
	(36, 1, '여기에다 글 쓰고 싶어', 'ㅈㄱㄴ', '1c95ccb3-fc4d-41ee-8424-e08aba892cca', 0, '2024-06-05 17:45:11', '2024-06-05 17:45:11');

-- 테이블 데이터 rcdb.RC_BOARD_BBS:~3 rows (대략적) 내보내기
INSERT INTO `RC_BOARD_BBS` (`UID`, `PATH`, `TITLE`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(1, 'notice', '공지사항', '2024-05-26 03:50:57', '2024-05-26 03:51:46'),
	(2, 'free', '자유게시판', '2024-05-26 03:51:02', '2024-05-26 03:51:59'),
	(3, 'qna', 'QnA', '2024-05-26 03:51:09', '2024-05-26 03:51:52');

-- 테이블 데이터 rcdb.RC_BOARD_COMMENT:~1 rows (대략적) 내보내기

-- 테이블 데이터 rcdb.RC_BOARD_FILE:~3 rows (대략적) 내보내기
INSERT INTO `RC_BOARD_FILE` (`UID`, `BOARD_UID`, `FILE_UID`, `FILE_NAME`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(78, 36, '1c713214-d6c4-4486-b21c-ae9366def7cb', 'qwer.b', '2024-06-06 07:15:14', '2024-06-06 07:15:14'),
	(79, 36, '51fecdf7-52ca-4929-a2cf-b863c531f40a', 'qwer.c', '2024-06-06 07:15:14', '2024-06-06 07:15:14'),
	(84, 36, '1c713214-d6c4-4486-b21c-ae9366def7cb', 'qwer.b', '2024-06-06 07:15:24', '2024-06-06 07:15:24');

-- 테이블 데이터 rcdb.RC_FILE:~6 rows (대략적) 내보내기
INSERT INTO `RC_FILE` (`UID`, `PATH`, `NAME`, `SIZE`, `MD5`, `CREATED_AT`, `UPDATED_AT`) VALUES
	('1c713214-d6c4-4486-b21c-ae9366def7cb', '/tmp', '85220a18-7e58-4d7d-b263-520a6acf297a', 396437, '5ba489b8350d08ee50633bae316ab485', '2024-06-05 17:44:14', '2024-06-05 17:44:14'),
	('34c86485-b531-400e-ae4a-b17d70293692', '/tmp', '6f40aa70-3228-49d0-8ab5-e9f42ef6ea03', 2849764, 'afd8d5ead50ae6cc67f0b151a3e7f329', '2024-06-05 17:44:15', '2024-06-05 17:44:15'),
	('51fecdf7-52ca-4929-a2cf-b863c531f40a', '/tmp', 'bf9d4e6f-03ea-45aa-a7ce-68df6ab157f1', 2498849, '71f99cb8b45ffb89a53844a68f749033', '2024-06-05 17:44:14', '2024-06-05 17:44:14'),
	('7bb30e1e-e324-43ca-83ea-872f6ff1163c', '/tmp', 'c055c978-5172-48ee-9cb8-cc1d9accbb71', 803505, 'bf9be7074173666f90b7abefaf67fb36', '2024-06-05 17:44:14', '2024-06-05 17:44:14'),
	('9d1e22e2-0161-463c-b307-850c6c25cc92', '/tmp', 'df4cbec1-6bb8-4d66-ac2c-e3923c8f905d', 438272, 'd2524e0158f619740cad470739a133da', '2024-06-05 17:44:14', '2024-06-05 17:44:14'),
	('cc49bff1-fffd-41c8-a9b0-746879c0a7bf', '/tmp', '04b2d24c-1b2c-44fd-957a-83f11b505059', 168070, 'd41d8cd98f00b204e9800998ecf8427e', '2024-06-05 17:43:36', '2024-06-05 17:43:36');

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

-- 테이블 데이터 rcdb.RC_USER_RESOURCE:~18 rows (대략적) 내보내기
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
	(12, 'ALL', '/comment/**', '게시글 댓글 모든 권한', '2024-05-27 16:03:07', '2024-05-31 13:57:28'),
	(13, 'POST', '/board/*/*/file', '게시글 첨부파일 등록', '2024-06-06 06:40:50', '2024-06-06 06:42:10'),
	(14, 'GET', '/board/*/*/file', '게시글 첨부파일 목록 불러오기', '2024-06-06 06:41:19', '2024-06-06 06:42:07'),
	(15, 'DELETE', '/board/*/*/file', '게시글 첨부파일 삭제', '2024-06-06 06:42:05', '2024-06-06 07:18:10'),
	(16, 'POST', '/file', '파일 업로드', '2024-06-06 06:45:23', '2024-06-06 06:45:23'),
	(17, 'GET', '/file/*', '파일 다운로드', '2024-06-06 06:45:36', '2024-06-06 06:45:36'),
	(18, 'GET', '/bbs', '게시판 목록 불러오기', '2024-06-06 06:45:36', '2024-06-06 06:45:36');

-- 테이블 데이터 rcdb.RD_ROLE_RESOURCE:~29 rows (대략적) 내보내기
INSERT INTO `RD_ROLE_RESOURCE` (`UID`, `LEVEL_UID`, `RESOURCE_UID`, `CREATED_AT`, `UPDATED_AT`) VALUES
	(1, 1, 2, '2024-05-25 10:22:17', '2024-05-25 10:22:17'),
	(2, 1, 18, '2024-05-25 10:22:17', '2024-05-25 10:22:17'),
	(3, 2, 2, '2024-05-25 10:23:13', '2024-05-25 10:23:13'),
	(4, 2, 3, '2024-05-26 03:34:17', '2024-05-26 03:34:17'),
	(5, 2, 4, '2024-05-25 10:22:32', '2024-05-25 10:22:32'),
	(6, 2, 5, '2024-05-26 03:34:17', '2024-05-26 03:34:17'),
	(7, 2, 6, '2024-05-26 03:34:17', '2024-05-26 03:34:17'),
	(8, 2, 14, '2024-06-06 06:48:43', '2024-06-06 06:48:43'),
	(9, 2, 18, '2024-05-25 10:23:13', '2024-05-25 10:23:13'),
	(10, 3, 2, '2024-05-25 10:24:30', '2024-05-25 10:24:30'),
	(11, 3, 3, '2024-05-26 03:34:30', '2024-05-26 03:34:30'),
	(12, 3, 4, '2024-05-25 10:24:21', '2024-05-25 10:24:21'),
	(13, 3, 5, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(14, 3, 6, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(15, 3, 13, '2024-06-06 06:44:05', '2024-06-06 06:44:05'),
	(16, 3, 14, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(17, 3, 15, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(18, 3, 16, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(19, 3, 17, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(20, 3, 18, '2024-05-25 10:24:45', '2024-05-25 10:24:45'),
	(21, 4, 7, '2024-05-25 10:25:21', '2024-05-25 10:31:33'),
	(22, 4, 12, '2024-05-25 10:25:21', '2024-05-25 10:31:33'),
	(23, 4, 13, '2024-05-25 10:25:21', '2024-05-25 10:31:33'),
	(24, 4, 14, '2024-05-25 10:25:21', '2024-05-25 10:31:33'),
	(25, 4, 15, '2024-05-25 10:25:21', '2024-05-25 10:31:33'),
	(26, 4, 16, '2024-06-06 08:24:42', '2024-06-06 08:24:42'),
	(27, 4, 17, '2024-06-06 08:24:42', '2024-06-06 08:24:42'),
	(28, 4, 18, '2024-06-06 08:24:42', '2024-06-06 08:24:42'),
	(29, 10, 1, '2024-05-25 08:34:38', '2024-05-25 08:34:38');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
