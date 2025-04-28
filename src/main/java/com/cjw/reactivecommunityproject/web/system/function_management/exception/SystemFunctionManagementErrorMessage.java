package com.cjw.reactivecommunityproject.web.system.function_management.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum SystemFunctionManagementErrorMessage implements RcBaseErrorMessage {
    DUPLICATE_FUNCTION_NAME(null, "중복된 name이 존재합니다.", HttpStatus.BAD_REQUEST)
    , NOT_FOUND_FUNCTION(null, "기능을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST)
    , NOT_FOUND_FUNCTION_DETAIL(null, "기능 상세를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST)
    , DUMMY(null, null, null);

    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;

    SystemFunctionManagementErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    SystemFunctionManagementErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
