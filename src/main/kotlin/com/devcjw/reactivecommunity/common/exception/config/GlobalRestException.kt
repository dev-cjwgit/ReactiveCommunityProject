package com.devcjw.reactivecommunity.common.exception.config

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalRestException {

    @ExceptionHandler(RcException::class)
    fun handle(rcException: RcException): String{
        /**
         * TODO Reactive 상황에서 Mono나 Flux에 대한 예외 처리 고민 필요
         */
        return rcException.errorMessage
    }
}