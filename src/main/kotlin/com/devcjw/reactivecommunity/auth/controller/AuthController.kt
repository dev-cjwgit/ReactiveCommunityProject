package com.devcjw.reactivecommunity.auth.controller

import com.devcjw.reactivecommunity.auth.model.domain.AuthRepTokenVO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqLoginDTO
import com.devcjw.reactivecommunity.auth.service.AuthService
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.PostMapping
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
    fun login(authRepTokenVO: AuthReqLoginDTO): Mono<AuthRepTokenVO>{
        return authService.login(authRepTokenVO)
    }
}