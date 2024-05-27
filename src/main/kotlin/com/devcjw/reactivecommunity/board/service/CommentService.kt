package com.devcjw.reactivecommunity.board.service

import com.devcjw.reactivecommunity.auth.model.entity.RcUser
import com.devcjw.reactivecommunity.board.model.domain.CommentRepListVO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqUpdateDTO
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CommentService {
    fun list(rcUser: RcUser, boardUid: String): Flux<RestResponseVO<CommentRepListVO>>

    fun insert(rcUser: RcUser, commentReqInsertDTO: CommentReqInsertDTO): Mono<RestResponseVO<Void>>

    fun update(rcUser: RcUser, commentReqUpdateDTO: CommentReqUpdateDTO): Mono<RestResponseVO<Void>>

    fun delete(rcUser: RcUser, commentUid: Long): Mono<RestResponseVO<Void>>
}