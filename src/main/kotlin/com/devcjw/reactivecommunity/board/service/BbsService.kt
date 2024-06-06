package com.devcjw.reactivecommunity.board.service

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.RepBbsListVO
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Flux

interface BbsService {
    fun list(rcUser: RcUserJwtClaims): Flux<RestResponseVO<RepBbsListVO>>
}