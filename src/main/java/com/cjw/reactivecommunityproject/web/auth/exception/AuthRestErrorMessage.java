package com.cjw.reactivecommunityproject.web.auth.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum AuthRestErrorMessage implements RcBaseErrorMessage {

    NOT_FOUND_EMAIL(null, "가입되지 않은 이메일입니다.", HttpStatus.BAD_REQUEST),
    EXIST_ADDED_EMAIL(null, "이미 등록된 이메일입니다.", HttpStatus.BAD_REQUEST),
    EXIST_ADDED_NICKNAME(null, "이미 등록된 별명입니다.", HttpStatus.BAD_REQUEST),
    INVALID_USER_PASSWORD(null, "비밀번호가 틀렸습니다..", HttpStatus.BAD_REQUEST),
    LISTEN_JOINED_USER(null, "가입 대기중인 계정입니다.", HttpStatus.BAD_REQUEST),
    REFUSE_JOINED_STATE(null, "가입 거절당한 계정입니다.", HttpStatus.BAD_REQUEST),
    BEN_JOINED_STATE(null, "일시정지 되어있는 계정입니다.", HttpStatus.BAD_REQUEST),
    WITHDRAW_JOINED_STATE(null, "탈퇴한 계정입니다.", HttpStatus.BAD_REQUEST),

    DUMMY(null, null, null);

    private final Long errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;

    AuthRestErrorMessage(Long errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    AuthRestErrorMessage(Long errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.isDisplay = isDisplay;
    }

    @Override
    public Long getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public Boolean isDisplay() {
        return isDisplay;
    }
}
