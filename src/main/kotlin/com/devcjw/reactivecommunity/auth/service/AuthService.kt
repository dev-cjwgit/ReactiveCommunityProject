package com.devcjw.reactivecommunity.auth.service

import com.devcjw.reactivecommunity.auth.model.domain.*
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Mono

interface AuthService {
    fun login(loginDTO: AuthReqLoginDTO): Mono<RestResponseVO<AuthRepTokenVO>>
    fun signup(authReqSignupDTO: AuthReqSignupDTO): Mono<RestResponseVO<Void>>

    fun check(authReqCheckDTO: AuthReqCheckDTO): Mono<RestResponseVO<Void>>
    fun reissue(authReqReissueDTO: AuthReqReissueDTO): Mono<RestResponseVO<AuthRepReissueVO>>

}