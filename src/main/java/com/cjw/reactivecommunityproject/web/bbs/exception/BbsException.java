package com.cjw.reactivecommunityproject.web.bbs.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import org.springframework.http.HttpStatus;

public class BbsException extends RcBaseException {


    public BbsException(RcBaseErrorMessage rcBaseErrorMessage) {
        super(rcBaseErrorMessage.getErrorCode()
                , rcBaseErrorMessage.getErrorMessage()
                , rcBaseErrorMessage.getHttpStatus()
                , rcBaseErrorMessage.isDisplay()
        );
    }

    @Override
    public Integer getErrorCode() {
        return super.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return super.errorMessage;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return super.httpStatus;
    }

    @Override
    public Boolean isDisplay() {
        return super.isDisplay;
    }
}
