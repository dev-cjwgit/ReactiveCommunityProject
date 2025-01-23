package com.cjw.reactivecommunityproject.web.log.exception.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum LogExceptionErrorMessage implements RcBaseErrorMessage {
    INVALID_INQUIRY_NUMBER_FORMAT(null, "잘못된 inquiry number 입니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_LOG_BY_INQUIRY_NUMBER(null, "해당하는 inquiry number 데이터를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    DUMMY(null, null, null);

    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;

    LogExceptionErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    LogExceptionErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
