package com.devcjw.reactivecommunity.board.dao.impl

import com.devcjw.reactivecommunity.board.dao.CommentDAO
import com.devcjw.reactivecommunity.board.model.entity.InCommentInsertVO
import com.devcjw.reactivecommunity.board.model.entity.OutCommentSelectVO
import com.devcjw.reactivecommunity.board.model.entity.InCommentUpdateVO
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
class CommentDAOImpl(
    val databaseClient: DatabaseClient
) : CommentDAO {
    override fun isCommentUid(uid: Long): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                RC_BOARD_COMMENT RBC
            WHERE
                RBC.`UID` = :board_uid
        """

        return databaseClient.sql(sql)
            .bind("board_uid", uid)
            .map { row, _ ->
                row.get(0, Long::class.java) ?: 0L
            }
            .one()
            .map { count -> count > 0 }
    }

    override fun isWriterComment(commentUid: Long, writerUid: String): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                RC_BOARD_COMMENT RBC
            WHERE
                RBC.`UID` = :board_uid
            AND
                RBC.`WRITER_UID` = :writer_uid
        """

        return databaseClient.sql(sql)
            .bind("board_uid", commentUid)
            .bind("writer_uid", writerUid)
            .map { row, _ ->
                row.get(0, Long::class.java) ?: 0L
            }
            .one()
            .map { count -> count > 0 }
    }

    override fun selectList(boardUid: Long): Flux<OutCommentSelectVO> {
        val sql = """
            SELECT
                RBC.`UID`,
                RBC.`WRITER_UID`,
                RBC.`CONTENTS`,
                RBC.`CREATED_AT`,
                RBC.`UPDATED_AT`
            FROM
                RC_BOARD_COMMENT RBC
            WHERE
                RBC.`BOARD_UID` = :board_uid
        """

        return databaseClient.sql(sql)
            .bind("board_uid", boardUid)
            .map { row, _ ->
                OutCommentSelectVO(
                    uid = row.get("uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    writerUid = row.get("writer_uid", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    contents = row.get("contents", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    createdAt = row.get("created_at", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    updatedAt = row.get("updated_at", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),

                    )
            }
            .all()
    }

    override fun insert(inCommentInsertVO: InCommentInsertVO): Mono<Void> {
        return databaseClient.sql(
            """
                INSERT INTO RC_BOARD_COMMENT (`BOARD_UID`,`WRITER_UID`,`CONTENTS`)
                VALUES (:board_uid,:writer_uid,:contents)
            """.trimIndent()
        )
            .bind("board_uid", inCommentInsertVO.boardUid)
            .bind("writer_uid", inCommentInsertVO.userUid)
            .bind("contents", inCommentInsertVO.contents)
            .then()
    }

    override fun update(inCommentUpdateVO: InCommentUpdateVO): Mono<Void> {
        return databaseClient.sql(
            """
                UPDATE RC_BOARD_COMMENT RBC
                SET 
                    RBC.`CONTENTS` = :contents
                WHERE RBC.`UID` = :uid
            """.trimIndent()
        )
            .bind("uid", inCommentUpdateVO.uid)
            .bind("contents", inCommentUpdateVO.contents)
            .then()
    }

    override fun delete(commentUid: Long): Mono<Void> {
        return databaseClient.sql(
            """
                DELETE FROM 
                    RC_BOARD_COMMENT
                WHERE
                    `UID` = :uid
            """.trimIndent()
        )
            .bind("uid", commentUid)
            .then()
    }
}