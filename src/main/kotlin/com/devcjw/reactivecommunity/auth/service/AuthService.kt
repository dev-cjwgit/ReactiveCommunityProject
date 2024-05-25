package com.devcjw.reactivecommunity.auth.service

import com.devcjw.reactivecommunity.auth.model.domain.*
import reactor.core.publisher.Mono

interface AuthService {
    fun login(loginDTO: AuthReqLoginDTO): Mono<AuthRepTokenVO>
    fun signup(authReqSignupDTO: AuthReqSignupDTO): Mono<Boolean>

    fun check(authReqCheckDTO: AuthReqCheckDTO): Mono<Boolean>
    fun reissue(authReqReissueDTO: AuthReqReissueDTO): Mono<AuthRepReissueVO>

}