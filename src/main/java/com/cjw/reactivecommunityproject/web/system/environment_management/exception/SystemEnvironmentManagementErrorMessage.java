package com.cjw.reactivecommunityproject.web.system.environment_management.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum SystemEnvironmentManagementErrorMessage implements RcBaseErrorMessage {
    NOT_FOUNT_ENV_CODE_DETAIL(null, "상세 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    DUMMY(null, null, null);

    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;

    SystemEnvironmentManagementErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    SystemEnvironmentManagementErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
