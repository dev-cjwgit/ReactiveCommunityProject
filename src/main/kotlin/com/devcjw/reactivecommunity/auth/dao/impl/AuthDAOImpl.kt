package com.devcjw.reactivecommunity.auth.dao.impl

import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.model.entity.OutAuthLevelResourcesVO
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
        // RC_ROLE_UID 2 = 일반 사용자
        return databaseClient.sql(
            """
            INSERT INTO RC_USER (`UID`,`EMAIL`,`RC_ROLE_UID`, `PASSWORD`,`NAME`,`NICKNAME`)
            VALUES (:uid,:email,2,:password,:name,:nickname)
        """.trimIndent()
        )
            .bind("uid", rcUserEntity.uid)
            .bind("email", rcUserEntity.email)
            .bind("password", rcUserEntity.pw)
            .bind("name", rcUserEntity.name)
            .bind("nickname", rcUserEntity.nickname)
            .then()
    }

    override fun selectUserLevelResource(): Flux<OutAuthLevelResourcesVO> {
        logger.info { "select User Level Resource" }
        val sql = """
            SELECT
                RR.`UID`,
                GROUP_CONCAT(CONCAT(RUR.`METHOD`, ',', RUR.`URL_PATTERN`) ORDER BY RUR.`URL_PATTERN`, RUR.`METHOD` SEPARATOR '|') AS resources
            FROM
                RC_ROLE RR
                    JOIN
                RC_ROLE_RESOURCE RRR ON RR.`UID` = RRR.`RC_ROLE_UID`
                    JOIN
                RC_RESOURCE RUR ON RUR.`UID` = RRR.`RC_RESOURCE_UID`
            GROUP BY
                RR.`UID`
        """

        return databaseClient.sql(sql)
            .map { row, _ ->
                OutAuthLevelResourcesVO(
                    levelUid = row.get("uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    resources = row.get("resources", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION)
                )
            }
            .all()
    }

}