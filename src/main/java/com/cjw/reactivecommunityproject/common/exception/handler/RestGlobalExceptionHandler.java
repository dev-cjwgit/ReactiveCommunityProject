package com.cjw.reactivecommunityproject.common.exception.handler;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestGlobalExceptionHandler {
    private final ApplicationEventPublisher publisher;


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponseVO<Void>> methodArgsNotValidaValueExceptionHandle(HttpMessageNotReadableException httpMessageNotReadableException) {
        log.warn("RestGlobalExceptionHandler.methodArgsNotValidaValueExceptionHandle: {}", httpMessageNotReadableException.getMessage());

        return new ResponseEntity<>(
                RestResponseVO.<Void>builder()
                        .result(false)
                        .message(RcCommonErrorMessage.INVALID_PARAMETER_DATA.getErrorMessage())
                        .code(RcCommonErrorMessage.INVALID_PARAMETER_DATA.getErrorCode())
                        .build()
                , RcCommonErrorMessage.INVALID_PARAMETER_DATA.getHttpStatus()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponseVO<Void>> methodArgsNotValidExceptionHandle(MethodArgumentNotValidException notValidException) {
        var bindingResult = notValidException.getBindingResult();

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
    public ResponseEntity<RestResponseVO<String>> rcExceptionHandle(RcBaseException baseException) {
        log.warn("RestGlobalExceptionHandler.rcExceptionHandle() : {}", baseException.getErrorMessage());
        baseException.getErrorCode();
        Integer errorCode;
        String message;

        String inquiryNumber;

        if (baseException.isDisplay()) {
            errorCode = baseException.getErrorCode();
            message = baseException.getErrorMessage();

            inquiryNumber = null;
        } else {
            errorCode = RcCommonErrorMessage.INQUIRE_TO_ADMIN.getErrorCode();
            message = RcCommonErrorMessage.INQUIRE_TO_ADMIN.getErrorMessage();

            inquiryNumber = String.valueOf(UUID.randomUUID());

            var exceptionDocument = ElasticsearchLogExceptionDocument.builder()
                    .inquiryNumber(inquiryNumber)
                    .message(baseException.getMessage())
                    .stackTrace(Arrays.toString(baseException.getStackTrace()))
                    .timestamp(ZonedDateTime.now())
                    .build();

            publisher.publishEvent(exceptionDocument);
            log.info("RestGlobalExceptionHandler.rcExceptionHandle() : publisher -> {}", exceptionDocument);
        }

        return new ResponseEntity<>(
                RestResponseVO.<String>builder()
                        .result(false)
                        .code(errorCode)
                        .message(message)
                        .data(inquiryNumber)
                        .build()
                , baseException.getHttpStatus()
        );
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestResponseVO<String>> unknownExceptionHandle(Throwable t) {
        log.error("RestGlobalExceptionHandler.unknownExceptionHandle()", t);
        String inquiryNumber = String.valueOf(UUID.randomUUID());

        try {
            var exceptionDocument = ElasticsearchLogExceptionDocument.builder()
                    .inquiryNumber(inquiryNumber)
                    .message(t.getMessage())
                    .stackTrace(Arrays.toString(t.getStackTrace()))
                    .timestamp(ZonedDateTime.now())
                    .build();

            publisher.publishEvent(exceptionDocument);
            log.info("RestGlobalExceptionHandler.unknownExceptionHandle() : publisher -> {}", exceptionDocument);
        } catch (Exception e) {
            log.error("RestGlobalExceptionHandler.unknownExceptionHandle()-elasticsearch exception", e);
        }
        return new ResponseEntity<>(
                RestResponseVO.<String>builder()
                        .result(false)
                        .code(RcCommonErrorMessage.UNKNOWN_EXCEPTION.getErrorCode())
                        .message(RcCommonErrorMessage.UNKNOWN_EXCEPTION.getErrorMessage())
                        .data(inquiryNumber)
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
