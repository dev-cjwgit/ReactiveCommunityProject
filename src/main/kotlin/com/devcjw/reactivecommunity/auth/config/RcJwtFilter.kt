package com.devcjw.reactivecommunity.auth.config

import com.devcjw.reactivecommunity.auth.service.JwtService
import lombok.RequiredArgsConstructor
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@RequiredArgsConstructor
class RcJwtFilter(
    private val jwtService: JwtService
) : WebFilter {


    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {


        return chain.filter(exchange)
    }
}