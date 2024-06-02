package com.devcjw.reactivecommunity.auth.dao.impl

import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.model.entity.AuthLevelResourcesVO
import com.devcjw.reactivecommunity.auth.model.entity.RcUserEntity
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.RequiredArgsConstructor
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
@RequiredArgsConstructor
class AuthDAOImpl(
    val databaseClient: DatabaseClient
) : AuthDAO {
    private val logger = KotlinLogging.logger {}

    override fun insertRcUser(rcUserEntity: RcUserEntity): Mono<Void> {
        logger.info { "insert Rc User : $rcUserEntity" }
        return databaseClient.sql(
            """
            INSERT INTO RC_USER (`UID`,`EMAIL`,`PW`,`NAME`,`NICKNAME`)
            VALUES (:uid,:email,:password,:name,:nickname)
        """.trimIndent()
        )
            .bind("uid", rcUserEntity.uid)
            .bind("email", rcUserEntity.email)
            .bind("password", rcUserEntity.pw)
            .bind("name", rcUserEntity.name)
            .bind("nickname", rcUserEntity.nickname)
            .then()
    }

    override fun selectUserLevelResource(): Flux<AuthLevelResourcesVO> {
        logger.info { "select User Level Resource" }
        val sql = """
            SELECT
                RUL.`UID`,
                GROUP_CONCAT(CONCAT(RUR.`METHOD`, ',', RUR.`PATTERN`) ORDER BY RUR.`PATTERN`, RUR.`METHOD` SEPARATOR '|') AS resources
            FROM
                RC_USER_LEVEL RUL
                    JOIN
                RD_ROLE_RESOURCE RRR ON RUL.`UID` = RRR.`LEVEL_UID`
                    JOIN
                RC_USER_RESOURCE RUR ON RRR.`RESOURCE_UID` = RUR.`UID`
            GROUP BY
                RUL.`UID`
        """

        return databaseClient.sql(sql)
            .map { row, _ ->
                AuthLevelResourcesVO(
                    levelUid = row.get("uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    resources = row.get("resources", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION)
                )
            }
            .all()
    }

}