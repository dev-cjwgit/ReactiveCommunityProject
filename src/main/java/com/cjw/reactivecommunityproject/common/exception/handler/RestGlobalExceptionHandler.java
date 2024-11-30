package com.cjw.reactivecommunityproject.common.exception.handler;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponseVO<Void>> methodArgsNotValidExceptionHandle(MethodArgumentNotValidException notValidException) {
        var bindingResult = notValidException.getBindingResult();
        var argsObject = bindingResult.getObjectName();

        var errorMessageList = CollectionUtils.emptyIfNull(bindingResult.getAllErrors())
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();

        log.warn("RestGlobalExceptionHandler.methodArgsNotValidExceptionHandle() : {}", notValidException.getMessage());
        return new ResponseEntity<>(
                RestResponseVO.<Void>builder()
                        .result(false)
                        .message(StringUtils.join(errorMessageList, "\n"))
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

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
