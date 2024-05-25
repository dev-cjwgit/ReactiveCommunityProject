package com.devcjw.reactivecommunity.auth.dao

import com.devcjw.reactivecommunity.auth.model.RcUser
import reactor.core.publisher.Mono

interface AuthDAO {
    fun insertRcUser(rcUser: RcUser): Mono<Void>
}