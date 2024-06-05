package com.devcjw.reactivecommunity.auth.config

import com.devcjw.reactivecommunity.auth.config.handler.RcServerAccessDeniedHandler
import com.devcjw.reactivecommunity.auth.config.handler.RcServerAuthenticationEntryPoint
import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.model.entity.OutRestfulEntity
import jakarta.annotation.PostConstruct
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

    @Bean
    fun roleMapping(): HashMap<Long, ArrayList<OutRestfulEntity>> {
        return hashMapOf()
    }

    @PostConstruct
    private fun init() {
        authDAO.selectUserLevelResource()
            .doOnNext { userLevelResources ->
                val resourcesList = userLevelResources.resources.split("|").map {
                    val (method, pattern) = it.split(",")
                    OutRestfulEntity(method, pattern)
                }
                roleMapping()[userLevelResources.levelUid] = ArrayList(resourcesList)
            }.subscribe()
    }
}