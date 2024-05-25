package com.devcjw.reactivecommunity.auth.config

import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono

class RcReactiveAuthorizationManager : ReactiveAuthorizationManager<AuthorizationContext> {
    override fun check(
        authentication: Mono<Authentication>,
        `object`: AuthorizationContext
    ): Mono<AuthorizationDecision> {

        return Mono.just(AuthorizationDecision(true))
    }
}