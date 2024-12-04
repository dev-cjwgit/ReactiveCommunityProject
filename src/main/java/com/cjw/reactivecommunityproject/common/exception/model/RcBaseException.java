package com.cjw.reactivecommunityproject.common.exception.model;


import org.springframework.http.HttpStatus;

public abstract class RcBaseException extends RuntimeException implements RcBaseErrorMessage {
    protected Integer errorCode;
    protected String errorMessage;
    protected HttpStatus httpStatus;
    protected Boolean isDisplay;

    protected RcBaseException(String errorMessage) {
        super(errorMessage);
    }
}
