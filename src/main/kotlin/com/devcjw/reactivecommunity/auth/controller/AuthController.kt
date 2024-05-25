package com.devcjw.reactivecommunity.auth.controller

import com.devcjw.reactivecommunity.auth.model.domain.*
import com.devcjw.reactivecommunity.auth.service.AuthService
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@Slf4j
@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody authRepTokenVO: AuthReqLoginDTO): Mono<AuthRepTokenVO> {
        return authService.login(authRepTokenVO)
    }

    @PostMapping("/signup")
    fun signup(@RequestBody authReqSignupDTO: AuthReqSignupDTO): Mono<Boolean> {
        return authService.signup(authReqSignupDTO)
    }

    @PostMapping("/check")
    fun check(@RequestBody authReqCheckDTO: AuthReqCheckDTO): Mono<Boolean> {
        return authService.check(authReqCheckDTO)
    }

    @PostMapping("/reissue")
    fun reissue(@RequestBody authReqReissueDTO: AuthReqReissueDTO): Mono<AuthRepReissueVO>{
        return authService.reissue(authReqReissueDTO)
    }
}