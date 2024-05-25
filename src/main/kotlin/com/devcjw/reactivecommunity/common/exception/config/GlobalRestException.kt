package com.devcjw.reactivecommunity.common.exception.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalRestException {

    @ExceptionHandler(Throwable::class)
    fun handle(t: Throwable): ResponseEntity<String> {
        t.printStackTrace()
        /**
         * TODO Reactive 상황에서 Mono나 Flux에 대한 예외 처리 고민 필요
         */
        return ResponseEntity("#{msg.common.unknown}", HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(RcException::class)
    fun handle(rcException: RcException): ResponseEntity<String> {
        /**
         * TODO Reactive 상황에서 Mono나 Flux에 대한 예외 처리 고민 필요
         */
        return ResponseEntity(rcException.errorMessage, rcException.httpStatus)
    }
}