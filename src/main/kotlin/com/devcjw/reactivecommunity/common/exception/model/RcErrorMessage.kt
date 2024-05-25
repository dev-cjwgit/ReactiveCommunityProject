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


    NOTHING("", "", HttpStatus.OK);
}