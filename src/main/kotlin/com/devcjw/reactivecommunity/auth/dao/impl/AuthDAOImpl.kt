package com.devcjw.reactivecommunity.auth.dao.impl

import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.model.RcUser
import lombok.RequiredArgsConstructor
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
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

}