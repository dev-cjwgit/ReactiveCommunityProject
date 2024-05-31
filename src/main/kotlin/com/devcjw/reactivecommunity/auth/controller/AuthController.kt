package com.devcjw.reactivecommunity.auth.controller

import com.devcjw.reactivecommunity.auth.model.domain.*
import com.devcjw.reactivecommunity.auth.service.AuthService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody authRepTokenVO: AuthReqLoginDTO): Mono<RestResponseVO<AuthRepTokenVO>> {
        return authService.login(authRepTokenVO)
    }

    @PostMapping("/signup")
    fun signup(@RequestBody authReqSignupDTO: AuthReqSignupDTO): Mono<RestResponseVO<Void>> {
        return authService.signup(authReqSignupDTO)
    }

    @PostMapping("/check")
    fun check(@RequestBody authReqCheckDTO: AuthReqCheckDTO): Mono<RestResponseVO<Void>> {
        return authService.check(authReqCheckDTO)
    }

    @PostMapping("/reissue")
    fun reissue(@RequestBody authReqReissueDTO: AuthReqReissueDTO): Mono<RestResponseVO<AuthRepReissueVO>> {
        return authService.reissue(authReqReissueDTO)
    }
}