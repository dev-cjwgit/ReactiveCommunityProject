package com.cjw.reactivecommunityproject.common.security.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum SecurityErrorMessage implements RcBaseErrorMessage {

    INVALID_TOKEN_PAYLOAD(null, "토큰 Payload 가 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN_STRUCT(null, "토큰 구조가 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(null, "토큰이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_TOKEN(null, "토큰을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

    NOT_FOUND_USER_SALT(null, "유저 개인 키를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR, false),

    DUMMY(null, null, null);

    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;

    SecurityErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    SecurityErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.isDisplay = isDisplay;
    }

    @Override
    public Integer getErrorCode() {
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
