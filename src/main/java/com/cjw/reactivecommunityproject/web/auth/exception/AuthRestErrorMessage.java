package com.cjw.reactivecommunityproject.web.auth.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum AuthRestErrorMessage implements RcBaseErrorMessage {
    NOT_FOUND_EMAIL(null, "가입되지 않은 이메일입니다.", HttpStatus.BAD_REQUEST),
    EXIST_ADDED_EMAIL(null, "이미 등록된 이메일입니다.", HttpStatus.BAD_REQUEST),
    EXIST_ADDED_NICKNAME(null, "이미 등록된 별명입니다.", HttpStatus.BAD_REQUEST),

    DUMMY(null, null, null);

    private final Long errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    AuthRestErrorMessage(Long errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
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
}
