package com.devcjw.reactivecommunity.auth.manager.impl

import com.devcjw.reactivecommunity.auth.manager.AuthManager
import com.devcjw.reactivecommunity.auth.model.entity.OutRestfulEntity
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import io.github.oshai.kotlinlogging.KotlinLogging
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
    private val roleMapping: HashMap<Long, ArrayList<OutRestfulEntity>>,
) : AuthManager {
    private val logger = KotlinLogging.logger {}

    private val antPathMatcher = AntPathMatcher()
    private fun checkAuthority(auth: Authentication, method: String, path: String): Mono<Boolean> {
        val authorityOptional = auth.authorities.stream().findFirst()
        logger.info { "checkAuthority : $auth, method : $method, path : $path" }
        return authorityOptional.map { authority ->
            val authorityValue = authority.authority.toLongOrNull()
            logger.info { "level : $authorityValue" }
            if (authorityValue != null && roleMapping.containsKey(authorityValue)) {
                val restfulVOList = roleMapping[authorityValue] ?: emptyList()
                Flux.fromIterable(restfulVOList)
                    .parallel()
                    .runOn(Schedulers.parallel())
                    .filter { restfulVO ->
                        val result = (restfulVO.method == method || restfulVO.method == "ALL") &&
                                antPathMatcher.match(restfulVO.pattern, path)
                        logger.info { "role matching : $method, $path | $restfulVO = $result" }
                        result
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

    override fun check(authentication: Mono<Authentication>, method: String, path: String): Mono<Boolean> {
        return authentication
            .filter {
                logger.info { "${authentication} authorization : ${it.isAuthenticated}" }
                it.isAuthenticated
            }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.ACCESS_DENIED_EXCEPTION)))
            .flatMap { auth ->
                checkAuthority(auth, method, path)
            }
            .onErrorReturn(false)
    }
}