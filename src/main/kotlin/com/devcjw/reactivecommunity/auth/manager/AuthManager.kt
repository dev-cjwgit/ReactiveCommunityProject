package com.devcjw.reactivecommunity.auth.manager

import org.springframework.security.core.Authentication
import reactor.core.publisher.Mono

interface AuthManager {
    fun check(authentication: Mono<Authentication>, method: String, path: String): Mono<Boolean>
}