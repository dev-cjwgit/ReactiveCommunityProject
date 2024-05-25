package com.devcjw.reactivecommunity.common.exception.config

import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import org.springframework.http.HttpStatus

class RcException : RuntimeException {
    val errorMessage: String
    val httpStatus: HttpStatus

    constructor(
        rcErrorMessage: RcErrorMessage
    ) : super(rcErrorMessage.message) {
        errorMessage = rcErrorMessage.message
        httpStatus = rcErrorMessage.httpStatus
    }

    constructor(
        errorMessage: String, httpStatus: HttpStatus
    ) : super(errorMessage) {
        this.errorMessage = errorMessage
        this.httpStatus = httpStatus
    }


}