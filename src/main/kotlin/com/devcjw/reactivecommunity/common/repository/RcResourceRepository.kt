package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcResourceEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcResourceRepository : ReactiveCrudRepository<RcResourceEntity, Long> {
    fun findByUid(uid: Long): Mono<RcResourceEntity>
}