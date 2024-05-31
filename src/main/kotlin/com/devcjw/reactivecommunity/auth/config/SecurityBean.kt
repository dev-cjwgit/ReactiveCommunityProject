package com.devcjw.reactivecommunity.auth.config

import com.devcjw.reactivecommunity.auth.config.handler.RcServerAccessDeniedHandler
import com.devcjw.reactivecommunity.auth.config.handler.RcServerAuthenticationEntryPoint
import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.manager.AuthManager
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler

@Configuration
@RequiredArgsConstructor
class SecurityBean(
    private val authDAO: AuthDAO,
    private val authManager: AuthManager,
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun rcRcServerAuthenticationEntryPoint(): ServerAuthenticationEntryPoint {
        return RcServerAuthenticationEntryPoint()
    }

    @Bean
    fun rcServerAccessDeniedHandler(): ServerAccessDeniedHandler {
        return RcServerAccessDeniedHandler()
    }


}