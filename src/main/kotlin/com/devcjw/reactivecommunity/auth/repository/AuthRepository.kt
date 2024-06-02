package com.devcjw.reactivecommunity.auth.repository

import com.devcjw.reactivecommunity.auth.model.entity.RcUserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono


@Repository
interface AuthRepository : ReactiveCrudRepository<RcUserEntity, Long> {
    fun findByEmail(email: String): Mono<RcUserEntity>

    fun findByNickname(nickname: String): Mono<RcUserEntity>
}