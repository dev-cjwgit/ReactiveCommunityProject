package com.cjw.reactivecommunityproject.web.log.exception.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import org.springframework.http.HttpStatus;

public class LogExceptionException extends RcBaseException {


    public LogExceptionException(RcBaseErrorMessage rcBaseErrorMessage) {
        super(rcBaseErrorMessage.getErrorMessage());
        this.errorCode = rcBaseErrorMessage.getErrorCode();
        this.errorMessage = rcBaseErrorMessage.getErrorMessage();
        this.httpStatus = rcBaseErrorMessage.getHttpStatus();
        this.isDisplay = rcBaseErrorMessage.isDisplay();
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
        return super.isDisplay ;
    }
}
