package com.devcjw.reactivecommunity.auth.dao

import com.devcjw.reactivecommunity.common.model.entity.RcUserEntity
import com.devcjw.reactivecommunity.auth.model.entity.OutAuthLevelResourcesVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AuthDAO {
    fun insertRcUser(rcUserEntity: RcUserEntity): Mono<Void>

    fun selectUserLevelResource(): Flux<OutAuthLevelResourcesVO>

    /** TODO: 유저 승인 API 개발 필요
    * fun acceptRcUser()
    */
}