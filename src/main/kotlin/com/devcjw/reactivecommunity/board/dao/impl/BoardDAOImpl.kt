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
            WHERE RBB.`UID` = :bbs_uid
        """

        return databaseClient.sql(sql)
            .bind("bbs_uid", bbsUid)
            .map { row, _ ->
                row.get(0, Long::class.java) ?: 0L
            }
            .one()
            .map { count -> count > 0 }
    }

    override fun selectList(bbsUid: Short): Flux<BoardSelectListVO> {
        TODO("Not yet implemented")
    }

    override fun selectDetail(boardUid: Long): Mono<BoardSelectDetailVO> {
        TODO("Not yet implemented")
    }

    override fun insert(boardInsertDTO: BoardInsertDTO): Mono<Void> {
        return databaseClient.sql(
            """
                INSERT INTO RC_BOARD (`BBS_UID`,`TITLE`,`CONTENTS`,`WRITER_UID`)
                VALUES (:bbsUid,:title,:contents,:writerUid)
            """.trimIndent()
        )
            .bind("bbsUid", boardInsertDTO.bbsUid)
            .bind("title", boardInsertDTO.title)
            .bind("contents", boardInsertDTO.contents)
            .bind("writerUid", boardInsertDTO.writerUid)
            .then()
    }

    override fun update(boardUpdateDTO: BoardUpdateDTO): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun delete(boardUid: Long): Mono<Void> {
        TODO("Not yet implemented")
    }
}