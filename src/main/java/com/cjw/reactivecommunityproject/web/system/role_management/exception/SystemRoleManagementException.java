package com.cjw.reactivecommunityproject.web.system.role_management.exception;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseErrorMessage;
import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import org.springframework.http.HttpStatus;

public class SystemRoleManagementException extends RcBaseException {


    public SystemRoleManagementException(RcBaseErrorMessage rcBaseErrorMessage) {
        super(rcBaseErrorMessage.getErrorCode(),
                rcBaseErrorMessage.getErrorMessage()
                , rcBaseErrorMessage.getHttpStatus()
                , rcBaseErrorMessage.isDisplay());
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
