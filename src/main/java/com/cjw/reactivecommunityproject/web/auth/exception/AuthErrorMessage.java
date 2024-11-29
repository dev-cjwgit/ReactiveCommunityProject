package com.cjw.reactivecommunityproject.web.auth.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum AuthErrorMessage implements RcBaseErrorMessage {
    NOT_FOUNT_RC_USER(null, "not found user info", HttpStatus.NOT_FOUND);

    private final Long errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    AuthErrorMessage(Long errorCode, String errorMessage, HttpStatus httpStatus) {
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
