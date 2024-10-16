package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcRegionEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcRegionRepository : ReactiveCrudRepository<RcRegionEntity, String> {
    fun findByRegion(region: String): Mono<RcRegionEntity>
}