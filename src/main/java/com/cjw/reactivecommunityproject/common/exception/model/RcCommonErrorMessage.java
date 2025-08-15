package com.cjw.reactivecommunityproject.common.exception.model;

import org.springframework.http.HttpStatus;

public enum RcCommonErrorMessage implements RcBaseErrorMessage {
    NOT_FOUND_ENV_CODE(null, "환경 코드를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR, true)
    , INVALID_ENV_CODE(null, "올바르지 않은 환경 코드입니다.", HttpStatus.INTERNAL_SERVER_ERROR, true)
    , INVALID_PARAMETER_DATA(null, "올바르지 않은 파라미터입니다.", HttpStatus.BAD_REQUEST, true)
    , NOTFOUND_RESOURCE(null, "접근 가능한 URL이 존재하지 않습니다.", HttpStatus.NOT_FOUND, true)
    , UNAUTHORIZED_USER(null, "인증 되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED, true)
    , INVALID_ROLE_UID(null, "유효하지 않은 유저 권한 입니다.", HttpStatus.UNAUTHORIZED, true)
    , INQUIRE_TO_ADMIN(0, "관리자에게 문의하세요.", HttpStatus.INTERNAL_SERVER_ERROR, false)
    , UNAUTHORIZED_ACCESS(null, "해당 API 를 수행 할 권한이 없습니다.", HttpStatus.FORBIDDEN)
    , UNKNOWN_EXCEPTION(-1, "알 수 없는 예외입니다.", HttpStatus.INTERNAL_SERVER_ERROR, false);
    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;


    RcCommonErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    RcCommonErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
