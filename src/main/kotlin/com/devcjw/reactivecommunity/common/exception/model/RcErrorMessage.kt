package com.devcjw.reactivecommunity.common.exception.model

import org.springframework.http.HttpStatus

enum class RcErrorMessage(
    val message: String,
    val description: String,
    val httpStatus: HttpStatus
) {
    NOT_FOUND_USER_EMAIL_EXCEPTION("#{msg.auth.email_not_found}", "유저 이메일 정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_MATCH_USER_PASSWORD_EXCEPTION("#{msg.auth.password_not_match}", "유저 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    LISTEN_JOIN_USER_EXCEPTION("#{msg.auth.user_listen_join}", "가입 대기 상태입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_JOIN_EMAIL_EXCEPTION("#{msg.auth.exist_join_mail}", "이미 가입된 이메일입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_USE_NICKNAME_EXCEPTION("#{msg.auth.exist_join_mail}", "이미 사용중인 닉네임입니다.", HttpStatus.BAD_REQUEST),

    ACCESS_DENIED_EXCEPTION("#{msg.common.access_denied}", "권한이 부족합니다.", HttpStatus.FORBIDDEN),

    R2DBC_MAPPING_EXCEPTION("#{msg.common.mapping_exception}", "DB 맵핑 중 에러가 발생하였습니다.", HttpStatus.FORBIDDEN),

    DUPLICATE_LOGIN_EXCEPTION("#{msg.auth.already_login}", "이미 로그인 중인 계정입니다.", HttpStatus.BAD_REQUEST),

    NOT_MATCH_REFRESH_TOKEN_EXCEPTION("#{msg.auth_not_match_refresh_token}", "리프레시 토큰이 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_LOGIN_INFO_EXCEPTION("#{msg.auth_not_found_login_info}", "로그인 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),


    NOTHING("", "", HttpStatus.OK);
}