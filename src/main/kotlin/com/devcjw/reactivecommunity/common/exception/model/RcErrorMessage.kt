package com.devcjw.reactivecommunity.common.exception.model

import org.springframework.http.HttpStatus

enum class RcErrorMessage(
    val message: String,
    val description: String,
    val httpStatus: HttpStatus
) {
    UNKNOWN_EXCEPTION("{msg.common.unknown}","알 수 없는 예외가 발생하였습니다." , HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND_USER_EMAIL_EXCEPTION("#{msg.auth.email_not_found}", "유저 이메일 정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_MATCH_USER_PASSWORD_EXCEPTION("#{msg.auth.password_not_match}", "유저 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    LISTEN_JOIN_USER_EXCEPTION("#{msg.auth.user_listen_join}", "가입 대기 상태입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_JOIN_EMAIL_EXCEPTION("#{msg.auth.exist_join_mail}", "이미 가입된 이메일입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_USE_NICKNAME_EXCEPTION("#{msg.auth.exist_join_mail}", "이미 사용중인 닉네임입니다.", HttpStatus.BAD_REQUEST),

    AUTHENTICATION_EXCEPTION("#{msg.common.authentication}", "인증에 실패하였습니다.", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED_EXCEPTION("#{msg.common.access_denied}", "권한이 부족합니다.", HttpStatus.FORBIDDEN),
    NOT_MATCH_WRITER_UID_EXCEPTION("#{msg.common.not_match_writer}", "작성자와 일치하지 않습니다.", HttpStatus.FORBIDDEN),

    R2DBC_MAPPING_EXCEPTION("#{msg.common.mapping_exception}", "DB 맵핑 중 에러가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    DUPLICATE_LOGIN_EXCEPTION("#{msg.auth.already_login}", "이미 로그인 중인 계정입니다.", HttpStatus.BAD_REQUEST),

    NOT_MATCH_REFRESH_TOKEN_EXCEPTION("#{msg.auth.not_match_refresh_token}", "리프레시 토큰이 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_LOGIN_INFO_EXCEPTION("#{msg.auth.not_found_login_info}", "로그인 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

    NOT_FOUND_BBS_BOARD_EXCEPTION("#{msg.board.not_found_bbs_board}", "게시판을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_BOARD_EXCEPTION("#{msg.board.not_found_board}", "게시글을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_FILE_DB_EXCEPTION("#{msg.board.not_found_file_to_db}", "파일을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_FILE_NAS_EXCEPTION("#{msg.board.not_found_file_to_nas}", "파일을 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND_PATH_SAVE_FILE_EXCEPTION("#{msg.board.not_found_path}", "파일을 저장할 경로가 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    NOT_FOUND_COMMENT_EXCEPTION("#{msg.board.not_found_comment}", "댓글을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

    NOTHING("", "", HttpStatus.OK);
}