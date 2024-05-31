package com.devcjw.reactivecommunity.auth.manager.impl

import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.manager.AuthManager
import com.devcjw.reactivecommunity.auth.model.entity.RestfulVO
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import lombok.RequiredArgsConstructor
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Component
@RequiredArgsConstructor
class AuthManagerImpl(
    private val roleMapping: HashMap<Long, ArrayList<RestfulVO>>,
) : AuthManager {
    private val antPathMatcher = AntPathMatcher()

    override fun check(authentication: Mono<Authentication>, method: String, path: String): Mono<Boolean> {
        return authentication
            .filter { it.isAuthenticated }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.ACCESS_DENIED_EXCEPTION)))

            .flatMap { auth ->
                val authorityOptional = auth.authorities.stream().findFirst()
                authorityOptional.map { authority ->
                    val authorityValue = authority.authority.toLongOrNull()
                    if (authorityValue != null && roleMapping.containsKey(authorityValue)) {
                        val restfulVOList = roleMapping[authorityValue] ?: emptyList()
                        Flux.fromIterable(restfulVOList)
                            .parallel()
                            .runOn(Schedulers.parallel())
                            .filter { restfulVO ->
                                (restfulVO.method == method || restfulVO.method == "ALL") &&
                                        antPathMatcher.match(restfulVO.pattern, path)
                            }
                            .sequential()
                            .count()
                            .map { count -> count > 0 }
                    } else {
                        Mono.just(false)
                    }
                }.orElseGet {
                    Mono.just(false)
                }
            }

            .onErrorReturn(false)

    }
}