package com.cjw.reactivecommunityproject.web.board.bbs.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import org.springframework.http.HttpStatus;

public enum BoardBbsErrorMessage implements RcBaseErrorMessage {
    DUPLICATE_PATH(null, "이미 존재하는 Path 입니다.", HttpStatus.BAD_REQUEST)
    , DUPLICATE_NAME(null, "이미 존재하는 Name 입니다.", HttpStatus.BAD_REQUEST)
    , NOT_FOUND_BBS_UID(null, "존재하지 않는 게시판입니다.", HttpStatus.BAD_REQUEST)
    , DUMMY(null, null, null);



    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final Boolean isDisplay;

    BoardBbsErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this(errorCode, errorMessage, httpStatus, true);
    }

    BoardBbsErrorMessage(Integer errorCode, String errorMessage, HttpStatus httpStatus, Boolean isDisplay) {
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
