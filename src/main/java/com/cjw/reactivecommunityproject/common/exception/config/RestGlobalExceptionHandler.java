package com.cjw.reactivecommunityproject.common.exception.config;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import com.cjw.reactivecommunityproject.common.model.response.RestResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestGlobalExceptionHandler {
    @ExceptionHandler(RcBaseException.class)
    public ResponseEntity<RestResponseVO<Void>> rcExceptionHandle(RcBaseException baseException) {
        log.warn("RestGlobalExceptionHandler.rcExceptionHandle() : {}", baseException.getErrorMessage());
        return new ResponseEntity<>(
                RestResponseVO.<Void>builder()
                        .result(false)
                        .code(baseException.getErrorCode())
                        .message(baseException.getErrorMessage())
                        .build()
                , baseException.getHttpStatus()
        );
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestResponseVO<Void>> unknownExceptionHandle(Throwable t) {
        log.error("RestGlobalExceptionHandler.unknownExceptionHandle()", t);
        return new ResponseEntity<>(
                RestResponseVO.<Void>builder()
                        .result(false)
                        .message("unknown exception")
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
