package com.devcjw.reactivecommunity.auth.config.handler

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class RcServerAuthenticationEntryPoint : ServerAuthenticationEntryPoint {
    private val logger = KotlinLogging.logger {}

    override fun commence(exchange: ServerWebExchange, ex: AuthenticationException): Mono<Void> {
        logger.info { "authentication entry point : $exchange" }
        exchange.response.statusCode = HttpStatus.UNAUTHORIZED
        exchange.response.headers.set("WWW-Authenticate", "Bearer realm=\"Access to the protected resource\"")
        return exchange.response.setComplete()
    }
}