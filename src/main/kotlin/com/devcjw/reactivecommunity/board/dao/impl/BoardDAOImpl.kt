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
                rc_bbs RB
            WHERE RB.`uid` = :bbs_uid
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
                rc_bbs RB
            WHERE RB.`path` = :path
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
                rc_board RB
            WHERE RB.`uid` = :uid
        """

        return databaseClient.sql(sql)
                .bind("uid", uid)
                .map { row, _ ->
                    row.get(0, Long::class.java) ?: 0L
                }
                .one()
                .map { count -> count > 0 }
    }

    override fun isWriterBoard(boardUid: Long, createdUserUid: String): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                rc_board RB
            WHERE
                RB.`uid` = :board_uid
            AND
                RB.`created_user_uid` = :created_user_uid
        """

        return databaseClient.sql(sql)
                .bind("board_uid", boardUid)
                .bind("created_user_uid", createdUserUid)
                .map { row, _ ->
                    row.get(0, Long::class.java) ?: 0L
                }
                .one()
                .map { count -> count > 0 }
    }

    override fun isExistBoardFile(boardUid: Long, fileUid: String): Mono<Boolean> {
        val sql = """
            SELECT
                COUNT(*)
            FROM
                rc_board_file RBF
            WHERE
                RBF.`board_uid` = :board_uid
            AND
                RBF.`file_uid` = :file_uid
        """

        return databaseClient.sql(sql)
                .bind("board_uid", boardUid)
                .bind("file_uid", boardUid)
                .map { row, _ ->
                    row.get(0, Long::class.java) ?: 0L
                }
                .one()
                .map { count -> count > 0 }
    }

    override fun selectList(bbsPath: String): Flux<OutBoardSelectListVO> {
        val sql = """
            SELECT
                RB.`uid`,
                RB.`title`,
                RU.`nickname`,
                RB.`hit`,
                RB.`created_utc_at`,
                RB.`updated_utc_at`
            FROM
                rc_board RB
            LEFT JOIN
                rc_user RU
            ON
                RB.`created_user_uid` = RU.`uid` 
            WHERE
                RB.`bbs_uid` = (
                    SELECT RBB.`uid` FROM rc_bbs RBB WHERE RBB.`path` = :bbs_path
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
                            createdUserNickname = row.get("nickname", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            hit = row.get("hit", Int::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            createdUtcAt = row.get("created_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            updatedUtcAt = row.get("updated_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),

                            )
                }
                .all()
    }

    override fun selectDetail(boardUid: Long): Mono<OutBoardSelectDetailVO> {
        val sql = """
            SELECT
                RB.`uid`,
                RB.`title`,
                RB.`contents`,
                RU.`nickname`,
                RB.`hit`,
                RB.`created_utc_at`,
                RB.`updated_utc_at`
            FROM
                rc_board RB 
            LEFT JOIN 
                rc_user RU
            ON 
                RB.`created_user_uid` = RU.`uid`
            WHERE
                RB.`uid` = :board_uid
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
                            createdUtcAt = row.get("created_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            updatedUtcAt = row.get("updated_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    )
                }.one()
    }


    @Transactional
    override fun insert(inBoardInsertVO: InBoardInsertVO): Mono<Long> {
        return databaseClient.sql(
                """
                INSERT INTO rc_board (`bbs_uid`, `title`, `contents`, `created_user_uid`)
                VALUES (:bbs_uid, :title, :contents, :created_user_uid)
            """.trimIndent()
        )
                .bind("bbs_uid", inBoardInsertVO.bbsUid)
                .bind("title", inBoardInsertVO.title)
                .bind("contents", inBoardInsertVO.contents)
                .bind("created_user_uid", inBoardInsertVO.createdUserUid)
                .then()

                .then(databaseClient.sql("SELECT LAST_INSERT_ID() AS last_uid")
                        .map { row, _ -> row.get("last_uid", Long::class.java) ?: -1L }
                        .one()
                )
    }

    override fun update(inBoardUpdateVO: InBoardUpdateVO): Mono<Void> {
        return databaseClient.sql(
                """
                UPDATE rc_board RB
                SET RB.`title` = :title,
                    RB.`contents` = :contents
                WHERE RB.`uid` = :uid
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
                    rc_board
                WHERE
                    `uid` = :uid
            """.trimIndent()
        )
                .bind("uid", boardUid)
                .then()
    }

    override fun insertFile(inBoardInsertFileVO: InBoardInsertFileVO): Mono<Void> {
        return databaseClient.sql(
                """
                INSERT INTO rc_board_file (`board_uid`,`file_uid`,`file_name`)
                VALUES (:board_uid,:file_uid,:file_name)
            """.trimIndent()
        )
                .bind("board_uid", inBoardInsertFileVO.boardUid)
                .bind("file_uid", inBoardInsertFileVO.fileUid)
                .bind("file_name", inBoardInsertFileVO.fileName)
                .then()
    }

    override fun deleteFile(boardUid: Long, fileUid: String): Mono<Void> {
        val sql = """
            DELETE FROM 
                rc_board_file
            WHERE
                `board_uid` = :board_uid
            AND
                `file_uid` = :file_uid
        """

        return databaseClient.sql(sql)
                .bind("board_uid", boardUid)
                .bind("file_uid", fileUid)
                .then()
    }

    override fun selectFileList(boardUid: Long): Flux<OutBoardFileListVO> {
        val sql = """
            SELECT
                RBF.`board_uid`,
                RBF.`file_uid`,
                RBF.`file_name`,
                RF.`size`
            FROM
                rc_board_file RBF
            LEFT JOIN
                rc_file RF
            ON
                RBF.`file_uid` = RF.`uid`
            WHERE
                RBF.`board_uid` = :board_uid

        """

        return databaseClient.sql(sql)
                .bind("board_uid", boardUid)
                .map { row, _ ->
                    OutBoardFileListVO(
                            uid = row.get("uid", Long::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),

                            fileUid = row.get("file_uid", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),

                            fileName = row.get("file_name", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),

                            fileSize = row.get("size", Int::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION)

                    )
                }
                .all()
    }
}