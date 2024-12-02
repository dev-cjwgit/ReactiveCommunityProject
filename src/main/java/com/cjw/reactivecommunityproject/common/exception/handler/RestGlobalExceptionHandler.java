package com.cjw.reactivecommunityproject.common.exception.handler;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.service.ElasticsearchLogExceptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ElasticsearchLogExceptionService elasticsearchLogExceptionService;

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
    public ResponseEntity<RestResponseVO<Void>> rcExceptionHandle(RcBaseException baseException) {
        log.warn("RestGlobalExceptionHandler.rcExceptionHandle() : {}", baseException.getErrorMessage());
        var errorCode = baseException.getErrorCode();
        var message = baseException.isDisplay() ? baseException.getErrorMessage() : "관리자에게 문의하세요.";
        return new ResponseEntity<>(
                RestResponseVO.<Void>builder()
                        .result(false)
                        .code(errorCode)
                        .message(message)
                        .build()
                , baseException.getHttpStatus()
        );
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestResponseVO<Void>> unknownExceptionHandle(Throwable t) {
        log.error("RestGlobalExceptionHandler.unknownExceptionHandle()", t);
        try {
            String inquiryNumber = String.valueOf(UUID.randomUUID());

            var exceptionDocument = ElasticsearchLogExceptionDocument.builder()
                    .inquiryNumber(inquiryNumber)
                    .message(t.getMessage())
                    .stackTrace(Arrays.toString(t.getStackTrace()))
                    .timestamp(ZonedDateTime.now())
                    .build();

            var document = elasticsearchLogExceptionService.insert(exceptionDocument);
            log.info("RestGlobalExceptionHandler.unknownExceptionHandle()-elasticsearch result : {}", document);
        } catch (Exception e) {
            log.error("RestGlobalExceptionHandler.unknownExceptionHandle()-elasticsearch exception", e);
        }
        return new ResponseEntity<>(
                RestResponseVO.<Void>builder()
                        .result(false)
                        .code(RcCommonErrorMessage.UNKNOWN_EXCEPTION.getErrorCode())
                        .message(RcCommonErrorMessage.UNKNOWN_EXCEPTION.getErrorMessage())
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
