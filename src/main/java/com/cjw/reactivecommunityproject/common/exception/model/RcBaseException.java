package com.cjw.reactivecommunityproject.common.exception.model;


import org.springframework.http.HttpStatus;

public abstract class RcBaseException extends RuntimeException implements RcBaseErrorMessage {
    protected Long errorCode;
    protected String errorMessage;
    protected HttpStatus httpStatus;
}
