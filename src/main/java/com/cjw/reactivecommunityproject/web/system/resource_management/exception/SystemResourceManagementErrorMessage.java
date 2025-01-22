package com.cjw.reactivecommunityproject.web.system.resource_management.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum SystemResourceManagementErrorMessage implements RcBaseErrorMessage {
    NOT_FOUND_RESOURCE_DETAIL(null, "상세 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_RESOURCE(null, "Resource 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_RESOURCE_INFO(null, "이미 중복된 데이터입니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ROLE_DETAIL(null, "존재하지 않는 롤 입니다.", HttpStatus.BAD_REQUEST),
    DUMMY(null, null, null);

    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;

    SystemResourceManagementErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    SystemResourceManagementErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
