package com.devcjw.reactivecommunity.board.dao.impl

import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.model.entity.BoardInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.BoardSelectDetailVO
import com.devcjw.reactivecommunity.board.model.entity.BoardSelectListVO
import com.devcjw.reactivecommunity.board.model.entity.BoardUpdateDTO
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import lombok.RequiredArgsConstructor
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

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

    override fun selectList(bbsPath: String): Flux<BoardSelectListVO> {
        val sql = """
            SELECT
                RB.UID,
                RB.TITLE,
                RU.NICKNAME,
                RB.HIT,
                RB.CREATED_AT,
                RB.UPDATED_AT
            FROM
                RC_BOARD RB
            LEFT JOIN
                RC_USER RU
            ON
                RB.WRITER_UID = RU.UID 
            WHERE
                RB.BBS_UID = (
                    SELECT UID FROM RC_BOARD_BBS RBB WHERE RBB.PATH = :bbs_path
                )
        """

        return databaseClient.sql(sql)
            .bind("bbs_path", bbsPath)
            .map { row, _ ->
                BoardSelectListVO(
                    uid = row.get("uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    title = row.get("title", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    writerNickname = row.get("nickname", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    hit = row.get("hit", Int::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    createdAt = row.get("created_at", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    updatedAt = row.get("updated_at", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),

                    )
            }
            .all()
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