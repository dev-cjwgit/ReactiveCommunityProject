package com.devcjw.reactivecommunity.board.dao

import com.devcjw.reactivecommunity.board.model.entity.OutBbsListVO
import reactor.core.publisher.Flux

interface BbsDAO {
    fun list(): Flux<OutBbsListVO>
}