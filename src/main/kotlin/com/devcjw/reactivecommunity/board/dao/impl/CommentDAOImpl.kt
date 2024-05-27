package com.devcjw.reactivecommunity.board.dao.impl

import com.devcjw.reactivecommunity.board.dao.CommentDAO
import com.devcjw.reactivecommunity.board.model.entity.CommentInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.CommentSelectVO
import com.devcjw.reactivecommunity.board.model.entity.CommentUpdateDTO
import lombok.RequiredArgsConstructor
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
@RequiredArgsConstructor
class CommentDAOImpl(
    val databaseClient: DatabaseClient
) : CommentDAO {
    override fun selectList(boardUid: Long): Flux<CommentSelectVO> {
        TODO("Not yet implemented")
    }

    override fun insert(commentInsertDTO: CommentInsertDTO): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun update(commentUpdateDTO: CommentUpdateDTO): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun delete(commentUid: Long): Mono<Void> {
        TODO("Not yet implemented")
    }
}