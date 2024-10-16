package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcBbsEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcBbsRepository : ReactiveCrudRepository<RcBbsEntity, Long> {
    fun findByUid(uid: Long): Mono<RcBbsEntity>
}