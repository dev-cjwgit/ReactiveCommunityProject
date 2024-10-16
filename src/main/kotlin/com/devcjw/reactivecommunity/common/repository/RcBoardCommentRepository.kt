package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcBoardCommentEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcBoardCommentRepository : ReactiveCrudRepository<RcBoardCommentEntity, Long> {
    fun findByUid(region: Long): Mono<RcBoardCommentEntity>
}