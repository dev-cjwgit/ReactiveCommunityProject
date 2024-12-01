package com.cjw.reactivecommunityproject.common.exception.model;

import org.springframework.http.HttpStatus;

public enum RcCommonErrorMessage implements RcBaseErrorMessage {
    NOT_FOUND_ENV_CODE(null, "환경 코드를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR, false),
    INVALID_ENV_CODE(null, "올바르지 않은 환경 코드입니다.", HttpStatus.INTERNAL_SERVER_ERROR, false)

    ;
    private final Long errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;


    RcCommonErrorMessage(Long errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    RcCommonErrorMessage(Long errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
