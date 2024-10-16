package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcBoardFileEntity
import com.devcjw.reactivecommunity.common.model.entity.RcRegionEntity
import com.devcjw.reactivecommunity.common.model.entity.key.RcBoardFileKey
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcBoardFileRepository : ReactiveCrudRepository<RcBoardFileEntity, RcBoardFileKey> {
    fun findByBoardUidAndFileUid(boardUid: Long, fileUid: String): Mono<RcRegionEntity>
}