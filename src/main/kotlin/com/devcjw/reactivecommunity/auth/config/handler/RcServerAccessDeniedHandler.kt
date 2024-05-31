package com.devcjw.reactivecommunity.auth.config.handler

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class RcServerAccessDeniedHandler : ServerAccessDeniedHandler {
    private val logger = KotlinLogging.logger {}

    override fun handle(exchange: ServerWebExchange, denied: AccessDeniedException): Mono<Void> {
        logger.info { "access denied handler : $exchange" }
        exchange.response.statusCode = HttpStatus.FORBIDDEN
        exchange.response.headers.set("WWW-Authenticate", "Bearer realm=\"access denied\"")
        return exchange.response.setComplete()
    }
}