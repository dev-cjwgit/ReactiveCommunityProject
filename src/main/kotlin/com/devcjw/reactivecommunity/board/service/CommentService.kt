package com.devcjw.reactivecommunity.board.service

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.CommentRepListVO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqUpdateDTO
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CommentService {
    fun list(rcUser: RcUserJwtClaims, boardUid: Long): Flux<RestResponseVO<CommentRepListVO>>

    fun insert(rcUser: RcUserJwtClaims, commentReqInsertDTO: CommentReqInsertDTO): Mono<RestResponseVO<Void>>

    fun update(rcUser: RcUserJwtClaims, commentReqUpdateDTO: CommentReqUpdateDTO): Mono<RestResponseVO<Void>>

    fun delete(rcUser: RcUserJwtClaims, commentUid: Long): Mono<RestResponseVO<Void>>
}