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

    NOTHING("", "", HttpStatus.OK);
}