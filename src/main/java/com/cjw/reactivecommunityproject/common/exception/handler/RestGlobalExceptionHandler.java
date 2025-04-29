package com.cjw.reactivecommunityproject.common.exception.handler;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.component.RcRedisIdGeneratorComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.UUID;
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
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestGlobalExceptionHandler {
    private final ApplicationEventPublisher publisher;
    private final RcRedisIdGeneratorComponent rcRedisIdGeneratorComponent;

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<RestResponseVO<Void>> noResourceFoundExceptionHandle(NoResourceFoundException noResourceFoundException) {
        log.warn("RestGlobalExceptionHandler.noResourceFoundExceptionHandle() : {}", noResourceFoundException.getMessage());

        return new ResponseEntity<>(
                RestResponseVO.<Void>builder()
                        .result(false)
                        .message(RcCommonErrorMessage.NOTFOUND_RESOURCE.getErrorMessage())
                        .code(RcCommonErrorMessage.NOTFOUND_RESOURCE.getErrorCode())
                        .build()
                , RcCommonErrorMessage.NOTFOUND_RESOURCE.getHttpStatus()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponseVO<Void>> methodArgsNotValidaValueExceptionHandle(HttpMessageNotReadableException httpMessageNotReadableException) {
        log.warn("RestGlobalExceptionHandler.methodArgsNotValidaValueExceptionHandle() : {}", httpMessageNotReadableException.getMessage());

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

        if (Boolean.TRUE.equals(baseException.isDisplay())) {
            errorCode = baseException.getErrorCode();
            message = baseException.getErrorMessage();

            inquiryNumber = null;
        } else {
            errorCode = RcCommonErrorMessage.INQUIRE_TO_ADMIN.getErrorCode();
            message = RcCommonErrorMessage.INQUIRE_TO_ADMIN.getErrorMessage();

            Long incrementId = rcRedisIdGeneratorComponent.getNextLogExceptionId();

            inquiryNumber = String.valueOf(UUID.randomUUID());

            var exceptionDocument = ElasticsearchLogExceptionDocument.builder()
                    .inquiryNumber(inquiryNumber)
                    .index(incrementId)
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
            Long incrementId = rcRedisIdGeneratorComponent.getNextLogExceptionId();

            inquiryNumber = String.valueOf(UUID.randomUUID());

            var exceptionDocument = ElasticsearchLogExceptionDocument.builder()
                    .inquiryNumber(inquiryNumber)
                    .index(incrementId)
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
