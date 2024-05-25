package com.devcjw.reactivecommunity.auth.dao.impl

import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.model.RcUser
import com.devcjw.reactivecommunity.auth.model.entity.AuthLevelResourcesVO
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
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

    override fun insertRcUser(rcUser: RcUser): Mono<Void> {
        return databaseClient.sql(
            """
            INSERT INTO RC_USER_INFO (`UID`,`EMAIL`,`PW`,`NAME`,`NICKNAME`)
            VALUES (:uid,:email,:password,:name,:nickname)
        """.trimIndent()
        )
            .bind("uid", rcUser.uid)
            .bind("email", rcUser.email)
            .bind("password", rcUser.pw)
            .bind("name", rcUser.name)
            .bind("nickname", rcUser.nickname)
            .then()
    }

    override fun selectUserLevelResource(): Flux<AuthLevelResourcesVO> {
        val sql = """
            SELECT
                RUL.UID AS level_uid,
                GROUP_CONCAT(CONCAT(RUR.METHOD, ',', RUR.PATTERN) ORDER BY RUR.PATTERN, RUR.METHOD SEPARATOR '|') AS resources
            FROM
                RC_USER_LEVEL RUL
                    JOIN
                RD_ROLE_RESOURCE RRR ON RUL.UID = RRR.LEVEL_UID
                    JOIN
                RC_USER_RESOURCE RUR ON RRR.RESOURCE_UID = RUR.UID
            GROUP BY
                RUL.UID
        """

        return databaseClient.sql(sql)
            .map { row, _ ->
                AuthLevelResourcesVO(
                    levelUid = row.get("level_uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    resources = row.get("resources", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION)
                )
            }
            .all()
    }

}