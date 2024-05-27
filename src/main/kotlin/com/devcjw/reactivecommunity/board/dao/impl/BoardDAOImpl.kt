package com.devcjw.reactivecommunity.board.dao.impl

import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.model.entity.BoardInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.BoardSelectDetailVO
import com.devcjw.reactivecommunity.board.model.entity.BoardSelectListVO
import com.devcjw.reactivecommunity.board.model.entity.BoardUpdateDTO
import lombok.RequiredArgsConstructor
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
@RequiredArgsConstructor
class BoardDAOImpl(
    val databaseClient: DatabaseClient
) : BoardDAO {
    override fun isBbsBoard(bbsUid: Short): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                RC_BOARD_BBS RBB
            WHERE UID = :bbsUid
        """

        return databaseClient.sql(sql)
            .bind("bbsUid", bbsUid)
            .map { row, _ ->
                row.get(0, Long::class.java) ?: 0L
            }
            .one()
            .map { count -> count > 0 }
    }

    override fun selectBoardList(): Flux<BoardSelectListVO> {
        TODO("Not yet implemented")
    }

    override fun selectBoardDetail(postUid: Long): Mono<BoardSelectDetailVO> {
        TODO("Not yet implemented")
    }

    override fun insertPost(boardInsertDTO: BoardInsertDTO): Mono<Void> {
         return databaseClient.sql(
            """
                INSERT INTO RC_BOARD_POST (`BBS_UID`,`TITLE`,`CONTENTS`,`WRITER_UID`)
                VALUES (:bbsUid,:title,:contents,:writerUid)
            """.trimIndent()
        )
            .bind("bbsUid", boardInsertDTO.bbsUid)
            .bind("title", boardInsertDTO.title)
            .bind("contents", boardInsertDTO.contents)
            .bind("writerUid", boardInsertDTO.writerUid)
            .then()
    }

    override fun updatePost(boardUpdateDTO: BoardUpdateDTO): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun deletePost(postUid: Long): Mono<Void> {
        TODO("Not yet implemented")
    }
}