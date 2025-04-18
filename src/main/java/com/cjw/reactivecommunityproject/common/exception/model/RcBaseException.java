package com.cjw.reactivecommunityproject.common.exception.model;


import org.springframework.http.HttpStatus;

public abstract class RcBaseException extends RuntimeException implements RcBaseErrorMessage {
    protected final Integer errorCode;
    protected final String errorMessage;
    protected final HttpStatus httpStatus;
    protected final Boolean isDisplay;

    protected RcBaseException(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.isDisplay = isDisplay;
    }
}
