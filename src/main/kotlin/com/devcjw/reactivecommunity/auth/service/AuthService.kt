package com.devcjw.reactivecommunity.auth.service

import com.devcjw.reactivecommunity.auth.model.domain.AuthRepTokenVO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqLoginDTO
import reactor.core.publisher.Mono

interface AuthService {
    fun login(loginDTO: AuthReqLoginDTO): Mono<AuthRepTokenVO>

}