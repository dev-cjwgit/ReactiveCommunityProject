package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcFileEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcFileRepository : ReactiveCrudRepository<RcFileEntity, String> {
    fun findByUid(uid: String): Mono<RcFileEntity>
}