package com.devcjw.reactivecommunity.board.dao

import com.devcjw.reactivecommunity.board.model.entity.CommentInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.CommentSelectVO
import com.devcjw.reactivecommunity.board.model.entity.CommentUpdateDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CommentDAO {
    fun isCommentUid(uid: Long): Mono<Boolean>

    fun isWriterComment(commentUid: Long, writerUid: String): Mono<Boolean>

    fun selectList(boardUid: Long): Flux<CommentSelectVO>

    fun insert(commentInsertDTO: CommentInsertDTO): Mono<Void>

    fun update(commentUpdateDTO: CommentUpdateDTO): Mono<Void>

    fun delete(commentUid: Long): Mono<Void>
}