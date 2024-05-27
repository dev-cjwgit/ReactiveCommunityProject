package com.devcjw.reactivecommunity.board.service

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.auth.model.entity.RcUser
import com.devcjw.reactivecommunity.board.model.domain.BoardRepDetailVO
import com.devcjw.reactivecommunity.board.model.domain.BoardRepListVO
import com.devcjw.reactivecommunity.board.model.domain.BoardReqInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.BoardReqUpdateDTO
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface BoardService {
    fun list(rcUser: RcUser, bbs: String): Flux<RestResponseVO<BoardRepListVO>>
    fun detail(rcUser: RcUser, boardUid: Long): Mono<RestResponseVO<BoardRepDetailVO>>
    fun insert(rcUser: RcUserJwtClaims, boardReqInsertDTO: BoardReqInsertDTO): Mono<RestResponseVO<Void>>
    fun update(rcUser: RcUser, boardReqUpdateDTO: BoardReqUpdateDTO): Mono<RestResponseVO<Void>>
    fun delete(rcUser: RcUser, boardUid: Long): Mono<RestResponseVO<Void>>
}