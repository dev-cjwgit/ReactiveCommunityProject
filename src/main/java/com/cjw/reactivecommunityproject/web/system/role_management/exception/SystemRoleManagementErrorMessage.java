package com.cjw.reactivecommunityproject.web.system.role_management.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum SystemRoleManagementErrorMessage implements RcBaseErrorMessage {
    DUPLICATE_ROLE_NAME(null, "중복된 name이 존재합니다.", HttpStatus.BAD_REQUEST)
    , DUPLICATE_ROLE_UID(null, "중복된 uid가 존재합니다.", HttpStatus.BAD_REQUEST)
    , NOT_FOUNT_ROLE(null, "uid를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST)
    , MAXIMUM_UID(null, "이미 자동으로 생성할 수 있는 최대 uid 를 초과했습니다. 직접 uid를 입력해주세요.", HttpStatus.BAD_REQUEST)

    , DUMMY(null, null, null);

    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;

    SystemRoleManagementErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    SystemRoleManagementErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
