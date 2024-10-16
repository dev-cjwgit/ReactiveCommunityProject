package com.devcjw.reactivecommunity.common.repository

import com.devcjw.reactivecommunity.common.model.entity.RcRoleResourceEntity
import com.devcjw.reactivecommunity.common.model.entity.key.RcRoleResourceKey
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RcRoleResourceRepository : ReactiveCrudRepository<RcRoleResourceEntity, RcRoleResourceKey> {
    fun findByRoleUidAndResourceUid(roleUid: Byte, resourceUid: Long): Mono<RcRoleResourceEntity>
}