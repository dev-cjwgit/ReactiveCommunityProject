package com.devcjw.reactivecommunity.board.service

import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.RepCommentListVO
import com.devcjw.reactivecommunity.board.model.domain.ReqCommentInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.ReqCommentUpdateVO
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CommentService {
    fun list(rcUser: RcUserJwtClaims, boardUid: Long): Flux<RestResponseVO<RepCommentListVO>>

    fun insert(rcUser: RcUserJwtClaims, reqCommentInsertDTO: ReqCommentInsertDTO): Mono<RestResponseVO<Void>>

    fun update(rcUser: RcUserJwtClaims, reqCommentUpdateVO: ReqCommentUpdateVO): Mono<RestResponseVO<Void>>

    fun delete(rcUser: RcUserJwtClaims, uid: Long): Mono<RestResponseVO<Void>>
}