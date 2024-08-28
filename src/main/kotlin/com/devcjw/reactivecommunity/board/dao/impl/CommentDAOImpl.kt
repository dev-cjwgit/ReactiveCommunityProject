package com.devcjw.reactivecommunity.board.dao.impl

import com.devcjw.reactivecommunity.board.dao.CommentDAO
import com.devcjw.reactivecommunity.board.model.entity.InCommentInsertVO
import com.devcjw.reactivecommunity.board.model.entity.InCommentUpdateVO
import com.devcjw.reactivecommunity.board.model.entity.OutCommentSelectVO
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
                rc_board_comment RBC
            WHERE
                RBC.`uid` = :board_uid
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
                rc_board_comment RBC
            WHERE
                RBC.`uid` = :board_uid
            AND
                RBC.`board_uid` = :writer_uid
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
                RBC.`uid`,
                RBC.`created_user_uid`,
                RBC.`contents`,
                RBC.`created_utc_at`,
                RBC.`updated_utc_at`
            FROM
                rc_board_comment RBC
            WHERE
                RBC.`board_uid` = :board_uid
        """

        return databaseClient.sql(sql)
                .bind("board_uid", boardUid)
                .map { row, _ ->
                    OutCommentSelectVO(
                            uid = row.get("uid", Long::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            createdUserUid = row.get("created_user_uid", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            contents = row.get("contents", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            createdUtcAt = row.get("created_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            updatedUtcAt = row.get("updated_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),

                            )
                }
                .all()
    }

    override fun insert(inCommentInsertVO: InCommentInsertVO): Mono<Void> {
        return databaseClient.sql(
                """
                INSERT INTO rc_board_comment (`board_uid`,`created_user_uid`,`CONTENTS`)
                VALUES (:board_uid,:writer_uid,:contents)
            """.trimIndent()
        )
                .bind("board_uid", inCommentInsertVO.boardUid)
                .bind("created_user_uid", inCommentInsertVO.createdUserUid)
                .bind("contents", inCommentInsertVO.contents)
                .then()
    }

    override fun update(inCommentUpdateVO: InCommentUpdateVO): Mono<Void> {
        return databaseClient.sql(
                """
                UPDATE rc_board_comment RBC
                SET 
                    RBC.`contents` = :contents
                WHERE RBC.`uid` = :uid
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
                    rc_board_comment
                WHERE
                    `uid` = :uid
            """.trimIndent()
        )
                .bind("uid", commentUid)
                .then()
    }
}