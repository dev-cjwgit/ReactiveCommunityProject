package com.devcjw.reactivecommunity.board.dao.impl

import com.devcjw.reactivecommunity.board.dao.BbsDAO
import com.devcjw.reactivecommunity.board.model.entity.OutBbsListVO
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.time.LocalDateTime

@Repository
class BbsDAOImpl(
    val databaseClient: DatabaseClient
) : BbsDAO {
    private val logger = KotlinLogging.logger {}
    override fun list(): Flux<OutBbsListVO> {
        val sql = """
            SELECT
                RB.`UID`,
                RB.`PATH`,
                RB.`NAME`,
                RB.`CREATED_AT`,
                RB.`UPDATED_AT`
            FROM
                RC_BBS RB
        """

        return databaseClient.sql(sql)
            .map { row, _ ->
                OutBbsListVO(
                    uid = row.get("uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    path = row.get("path", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    title = row.get("title", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    createdAt = row.get("created_at", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    updatedAt = row.get("updated_at", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                )
            }
            .all()
    }

}