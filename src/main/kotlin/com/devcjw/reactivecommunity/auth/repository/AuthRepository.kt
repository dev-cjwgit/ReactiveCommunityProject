package com.devcjw.reactivecommunity.auth.repository

import com.devcjw.reactivecommunity.auth.model.RcUser
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono


@Repository
interface AuthRepository : ReactiveCrudRepository<RcUser, Long> {
    fun findByEmail(email: String): Mono<RcUser>
}