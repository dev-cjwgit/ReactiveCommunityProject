package com.devcjw.reactivecommunity.auth.config.handler

import com.devcjw.reactivecommunity.auth.model.RestfulVO
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import lombok.RequiredArgsConstructor
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.util.AntPathMatcher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers


@RequiredArgsConstructor
class RcReactiveAuthorizationManager(
    private val roleMapping: Map<Long, List<RestfulVO>>
) : ReactiveAuthorizationManager<AuthorizationContext> {
    private val antPathMatcher = AntPathMatcher()

    override fun check(
        authentication: Mono<Authentication>,
        context: AuthorizationContext
    ): Mono<AuthorizationDecision> {
        return authentication
            .filter { it.isAuthenticated }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.ACCESS_DENIED_EXCEPTION)))

            .flatMap { auth ->
                val authorityOptional = auth.authorities.stream().findFirst()
                authorityOptional.map { authority ->
                    val authorityValue = authority.authority.toLongOrNull()
                    if (authorityValue != null && roleMapping.containsKey(authorityValue)) {
                        val restfulVOList = roleMapping[authorityValue] ?: emptyList()
                        val request = context.exchange.request
                        val path = request.path.toString()
                        val method = request.method.name()

                        Flux.fromIterable(restfulVOList)
                            .parallel()
                            .runOn(Schedulers.parallel())
                            .filter { restfulVO ->
                                (restfulVO.method == method || restfulVO.method == "ALL") &&
                                        antPathMatcher.match(restfulVO.pattern, path)
                            }
                            .sequential()
                            .count()
                            .map { count -> AuthorizationDecision(count > 0) }
                    } else {
                        Mono.just(AuthorizationDecision(false))
                    }
                }.orElseGet {
                    Mono.just(AuthorizationDecision(false))
                }
            }

            .onErrorReturn(AuthorizationDecision(false))

    }
}