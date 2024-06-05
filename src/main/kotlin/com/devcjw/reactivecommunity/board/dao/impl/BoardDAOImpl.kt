package com.devcjw.reactivecommunity.board.dao.impl

import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.model.entity.*
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import lombok.RequiredArgsConstructor
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime


@Repository
@RequiredArgsConstructor
class BoardDAOImpl(
    val databaseClient: DatabaseClient
) : BoardDAO {
    override fun isBbsUid(uid: Short): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                RC_BOARD_BBS RBB
            WHERE RBB.`UID` = :bbs_uid
        """

        return databaseClient.sql(sql)
            .bind("bbs_uid", uid)
            .map { row, _ ->
                row.get(0, Long::class.java) ?: 0L
            }
            .one()
            .map { count -> count > 0 }
    }

    override fun isBbsPath(path: String): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                RC_BOARD_BBS RBB
            WHERE RBB.`PATH` = :path
        """

        return databaseClient.sql(sql)
            .bind("path", path)
            .map { row, _ ->
                row.get(0, Long::class.java) ?: 0L
            }
            .one()
            .map { count -> count > 0 }
    }

    override fun isBoardUid(uid: Long): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                RC_BOARD RB
            WHERE RB.`UID` = :uid
        """

        return databaseClient.sql(sql)
            .bind("uid", uid)
            .map { row, _ ->
                row.get(0, Long::class.java) ?: 0L
            }
            .one()
            .map { count -> count > 0 }
    }

    override fun isWriterBoard(boardUid: Long, writerUid: String): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                RC_BOARD RB
            WHERE
                RB.`UID` = :board_uid
            AND
                RB.`WRITER_UID` = :writer_uid
        """

        return databaseClient.sql(sql)
            .bind("board_uid", boardUid)
            .bind("writer_uid", writerUid)
            .map { row, _ ->
                row.get(0, Long::class.java) ?: 0L
            }
            .one()
            .map { count -> count > 0 }
    }

    override fun selectList(bbsPath: String): Flux<OutBoardSelectListVO> {
        val sql = """
            SELECT
                RB.`UID`,
                RB.`TITLE`,
                RU.`NICKNAME`,
                RB.`HIT`,
                RB.`CREATED_AT`,
                RB.`UPDATED_AT`
            FROM
                RC_BOARD RB
            LEFT JOIN
                RC_USER RU
            ON
                RB.`WRITER_UID` = RU.`UID` 
            WHERE
                RB.`BBS_UID` = (
                    SELECT RBB.`UID` FROM RC_BOARD_BBS RBB WHERE RBB.`PATH` = :bbs_path
                )
        """

        return databaseClient.sql(sql)
            .bind("bbs_path", bbsPath)
            .map { row, _ ->
                OutBoardSelectListVO(
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

    override fun selectDetail(boardUid: Long): Mono<OutBoardSelectDetailVO> {
        val sql = """
            SELECT
                RB.`UID`,
                RB.`TITLE`,
                RB.`CONTENTS`,
                RU.`NICKNAME`,
                RB.`HIT`,
                RB.`CREATED_AT`,
                RB.`UPDATED_AT`
            FROM
                RC_BOARD RB 
            LEFT JOIN 
                RC_USER RU
            ON 
                RB.`WRITER_UID` = RU.`UID`
            WHERE
                RB.`UID` = :board_uid
        """

        return databaseClient.sql(sql)
            .bind("board_uid", boardUid)
            .map { row, _ ->
                OutBoardSelectDetailVO(
                    uid = row.get("uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    title = row.get("title", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    contents = row.get("contents", String::class.java)
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
            }.one()
    }


    @Transactional
    override fun insert(inBoardInsertVO: InBoardInsertVO): Mono<Long> {
        return databaseClient.sql(
            """
                INSERT INTO RC_BOARD (BBS_UID, TITLE, CONTENTS, WRITER_UID)
                VALUES (:bbs_uid, :title, :contents, :writer_uid)
            """.trimIndent()
        )
            .bind("bbs_uid", inBoardInsertVO.bbsUid)
            .bind("title", inBoardInsertVO.title)
            .bind("contents", inBoardInsertVO.contents)
            .bind("writer_uid", inBoardInsertVO.writerUid)
            .then() // Proceed to next operation after insert

            .then(databaseClient.sql("SELECT LAST_INSERT_ID() AS last_uid")
                .map { row, _ -> row.get("last_uid", Long::class.java) ?: -1L }
                .one()
            )
    }

    override fun update(inBoardUpdateVO: InBoardUpdateVO): Mono<Void> {
        return databaseClient.sql(
            """
                UPDATE RC_BOARD RB
                SET RB.`TITLE` = :title,
                    RB.`CONTENTS` = :contents
                WHERE RB.`UID` = :uid
            """.trimIndent()
        )
            .bind("uid", inBoardUpdateVO.uid)
            .bind("title", inBoardUpdateVO.title)
            .bind("contents", inBoardUpdateVO.contents)
            .then()
    }

    override fun delete(boardUid: Long): Mono<Void> {
        return databaseClient.sql(
            """
                DELETE FROM 
                    RC_BOARD
                WHERE
                    `UID` = :uid
            """.trimIndent()
        )
            .bind("uid", boardUid)
            .then()
    }

    override fun insertFile(inBoardInsertFileVO: InBoardInsertFileVO): Mono<Void> {
        return databaseClient.sql(
            """
                INSERT INTO RC_BOARD_FILE (`BOARD_UID`,`FILE_UID`,`FILE_NAME`)
                VALUES (:board_uid,:file_uid,:file_name)
            """.trimIndent()
        )
            .bind("board_uid", inBoardInsertFileVO.boardUid)
            .bind("file_uid", inBoardInsertFileVO.fileUid)
            .bind("file_name", inBoardInsertFileVO.fileName)
            .then()
    }

    override fun deleteFile(boardFileUid: Long): Mono<Void> {
        val sql = """
            DELETE FROM 
                RC_BOARD_FILE
            WHERE
                `UID` = :file_uid
        """

        return databaseClient.sql(sql)
            .bind("file_uid", boardFileUid)
            .then()
    }
}