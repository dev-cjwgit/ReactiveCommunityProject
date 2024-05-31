package com.devcjw.reactivecommunity.auth.config

import com.devcjw.reactivecommunity.auth.config.handler.RcReactiveAuthorizationManager
import com.devcjw.reactivecommunity.auth.config.handler.RcServerAccessDeniedHandler
import com.devcjw.reactivecommunity.auth.config.handler.RcServerAuthenticationEntryPoint
import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.model.entity.RestfulVO
import jakarta.annotation.PostConstruct
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler

@Configuration
@RequiredArgsConstructor
class SecurityBean(
    private val authDAO: AuthDAO,
) {
    private val roleMapping = hashMapOf<Long, ArrayList<RestfulVO>>()

    @PostConstruct
    private fun init() {
        authDAO.selectUserLevelResource()
            .doOnNext { userLevelResources ->
                // 파싱 및 roleMapping에 추가
                val resourcesList = userLevelResources.resources.split("|").map {
                    val (method, pattern) = it.split(",")
                    RestfulVO(method, pattern)
                }
                roleMapping[userLevelResources.levelUid] = ArrayList(resourcesList)
            }.subscribe()
    }

    @Bean
    fun rcReactiveAuthorizationManager(): ReactiveAuthorizationManager<AuthorizationContext> {
        return RcReactiveAuthorizationManager(roleMapping)
    }

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