package com.devcjw.reactivecommunity.board.dao

import com.devcjw.reactivecommunity.board.model.entity.InCommentInsertVO
import com.devcjw.reactivecommunity.board.model.entity.InCommentUpdateVO
import com.devcjw.reactivecommunity.board.model.entity.OutCommentSelectVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CommentDAO {
    fun isCommentUid(uid: Long): Mono<Boolean>

    fun isWriterComment(commentUid: Long, writerUid: String): Mono<Boolean>

    fun selectList(boardUid: Long): Flux<OutCommentSelectVO>

    fun insert(inCommentInsertVO: InCommentInsertVO): Mono<Void>

    fun update(inCommentUpdateVO: InCommentUpdateVO): Mono<Void>

    fun delete(commentUid: Long): Mono<Void>
}