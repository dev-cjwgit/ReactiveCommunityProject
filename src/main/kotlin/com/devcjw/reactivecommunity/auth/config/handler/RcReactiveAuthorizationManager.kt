package com.devcjw.reactivecommunity.auth.config.handler

import com.devcjw.reactivecommunity.auth.manager.AuthManager
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.RequiredArgsConstructor
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
@RequiredArgsConstructor
class RcReactiveAuthorizationManager(
    private val authManager: AuthManager,
) : ReactiveAuthorizationManager<AuthorizationContext> {
    private val logger = KotlinLogging.logger {}

    override fun check(
        authentication: Mono<Authentication>,
        context: AuthorizationContext
    ): Mono<AuthorizationDecision> {
        logger.info { "authorizationManager : $authentication" }
        return authManager
            .check(
                authentication,
                context.exchange.request.method.toString(),
                context.exchange.request.path.toString()
            )
            .map {
                AuthorizationDecision(it)
            }
    }
}