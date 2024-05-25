package com.devcjw.reactivecommunity.auth.service

import com.devcjw.reactivecommunity.auth.model.domain.AuthRepTokenVO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqCheckDTO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqLoginDTO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqSignupDTO
import reactor.core.publisher.Mono

interface AuthService {
    fun login(loginDTO: AuthReqLoginDTO): Mono<AuthRepTokenVO>
    fun signup(authReqSignupDTO: AuthReqSignupDTO): Mono<Boolean>

    fun check(authReqCheckDTO: AuthReqCheckDTO): Mono<Boolean>

}