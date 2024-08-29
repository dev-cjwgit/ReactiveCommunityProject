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
            INSERT INTO rc_user (`uid`, `email`, `role_uid`, `pw`, `name`, `nickname`, `joined_region`)
            VALUES (:uid, :email, 1, :password, :name, :nickname, :joined_region)
        """.trimIndent()
        )
                .bind("uid", rcUserEntity.uid)
                .bind("email", rcUserEntity.email)
                .bind("password", rcUserEntity.password)
                .bind("name", rcUserEntity.name)
                .bind("nickname", rcUserEntity.nickname)
                .bind("joined_region", rcUserEntity.joinedRegion)
                .then()
    }

    override fun selectUserLevelResource(): Flux<OutAuthLevelResourcesVO> {
        logger.info { "select User Level Resource" }
        val sql = """
            SELECT
                RR.`uid`,
                GROUP_CONCAT(CONCAT(RUR.`method`, ',', RUR.`url_pattern`) ORDER BY RUR.`url_pattern`, RUR.`method` SEPARATOR '|') AS resources
            FROM
                rc_role RR
                    JOIN
                rc_role_resource RRR ON RR.`uid` = RRR.`role_uid`
                    JOIN
                rc_resource RUR ON RUR.`uid` = RRR.`resource_uid`
            GROUP BY
                RR.`uid`
        """

        return databaseClient.sql(sql)
                .map { row, _ ->
                    OutAuthLevelResourcesVO(
                            roleUid = row.get("uid", Long::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            resources = row.get("resources", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION)
                    )
                }
                .all()
    }

}