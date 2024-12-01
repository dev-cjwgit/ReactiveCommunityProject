package com.cjw.reactivecommunityproject.common.exception.model;

import org.springframework.http.HttpStatus;

public interface RcBaseErrorMessage {
    Integer getErrorCode();
    String getErrorMessage();
    HttpStatus getHttpStatus();
    Boolean isDisplay();
}
