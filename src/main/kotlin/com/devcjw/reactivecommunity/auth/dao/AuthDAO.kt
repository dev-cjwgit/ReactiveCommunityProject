package com.devcjw.reactivecommunity.auth.dao

import com.devcjw.reactivecommunity.auth.model.entity.RcUser
import com.devcjw.reactivecommunity.auth.model.entity.AuthLevelResourcesVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AuthDAO {
    fun insertRcUser(rcUser: RcUser): Mono<Void>

    fun selectUserLevelResource(): Flux<AuthLevelResourcesVO>
}