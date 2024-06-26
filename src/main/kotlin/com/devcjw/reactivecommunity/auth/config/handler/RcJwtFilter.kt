package com.devcjw.reactivecommunity.auth.config.handler

import com.devcjw.reactivecommunity.auth.service.JwtService
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@RequiredArgsConstructor
class RcJwtFilter(
    private val jwtService: JwtService
) : WebFilter {
    private val logger = KotlinLogging.logger {}

    private fun extractToken(exchange: ServerWebExchange): String? {
        val authHeader = exchange.request.headers.getFirst("Authorization")
        return if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader.substring(7)
        } else {
            null
        }
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token = extractToken(exchange)
        logger.info { "Authentication Token : ${token}" }
        return if (token != null && jwtService.validateToken(token)) {
            val claims = jwtService.getRcUser(token)
            val authentication = UsernamePasswordAuthenticationToken(
                claims,
                null,
                listOf(SimpleGrantedAuthority(claims.level.toString()))
            )
            logger.info { "save Security Context : $authentication" }
            val securityContext = SecurityContextImpl(authentication)
            chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)))
        } else {
            chain.filter(exchange)
        }
    }
}