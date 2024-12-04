package com.cjw.reactivecommunityproject.common.exception.model;

import org.springframework.http.HttpStatus;

public enum RcCommonErrorMessage implements RcBaseErrorMessage {
    NOT_FOUND_ENV_CODE(null, "환경 코드를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR, false),
    INVALID_ENV_CODE(null, "올바르지 않은 환경 코드입니다.", HttpStatus.INTERNAL_SERVER_ERROR, false),

    INQUIRE_TO_ADMIN(0, "관리자에게 문의하세요.", HttpStatus.INTERNAL_SERVER_ERROR, false),
    UNKNOWN_EXCEPTION(-1, "알 수 없는 예외입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;


    RcCommonErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    RcCommonErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
