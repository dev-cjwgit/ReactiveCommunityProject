package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcUserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono


@Repository
interface RcUserRepository : ReactiveCrudRepository<RcUserEntity, Long> {
    fun findByEmail(email: String): Mono<RcUserEntity>

    fun findByNickname(nickname: String): Mono<RcUserEntity>
}