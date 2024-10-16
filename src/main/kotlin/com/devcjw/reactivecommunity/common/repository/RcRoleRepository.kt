package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcRoleEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcRoleRepository : ReactiveCrudRepository<RcRoleEntity, Byte> {
    fun findByUid(uid: Byte): Mono<RcRoleEntity>
}