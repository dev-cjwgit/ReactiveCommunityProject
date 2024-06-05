package com.devcjw.reactivecommunity.auth.service

import com.devcjw.reactivecommunity.auth.model.domain.*
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Mono

interface AuthService {
    fun login(loginDTO: ReqAuthLoginVO): Mono<RestResponseVO<RepAuthTokenVO>>
    fun signup(reqAuthSignupVO: ReqAuthSignupVO): Mono<RestResponseVO<Void>>

    fun check(reqAuthCheckVO: ReqAuthCheckVO): Mono<RestResponseVO<Void>>
    fun reissue(reqAuthReissueVO: ReqAuthReissueVO): Mono<RestResponseVO<RepAuthReissueVO>>

}