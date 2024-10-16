package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcBoardEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcBoardRepository : ReactiveCrudRepository<RcBoardEntity, Long> {
    fun findByUid(uid: Long): Mono<RcBoardEntity>
}