package com.cjw.reactivecommunityproject.common.exception.model;

import org.springframework.http.HttpStatus;

public interface RcBaseErrorMessage {
    Long getErrorCode();
    String getErrorMessage();
    HttpStatus getHttpStatus();
}
