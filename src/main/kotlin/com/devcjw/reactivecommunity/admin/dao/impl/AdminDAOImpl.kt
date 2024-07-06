package com.devcjw.reactivecommunity.admin.dao.impl

import com.devcjw.reactivecommunity.admin.dao.AdminDAO
import com.devcjw.reactivecommunity.admin.model.entity.*
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.RequiredArgsConstructor
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Repository
@RequiredArgsConstructor
class AdminDAOImpl(
    val databaseClient: DatabaseClient
) : AdminDAO {
    private val logger = KotlinLogging.logger {}


    override fun selectLevelList(): Flux<OutAdminLevelSelectVO> {
        val sql = "SELECT `UID`, `NAME`, `CREATED_AT`, `UPDATED_AT` FROM RC_ROLE"
        return databaseClient.sql(sql)
            .map { row, _ ->
                OutAdminLevelSelectVO(
                    uid = row.get("UID", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    name = row.get("NAME", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    createdAt = row.get("CREATED_AT", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    updatedAt = row.get("UPDATED_AT", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION)
                )
            }
            .all()
    }

    override fun insertLevel(inAdminLevelInsertVO: InAdminLevelInsertVO): Mono<Void> {
        val sql = "INSERT INTO RC_ROLE (`NAME`) VALUES (:name)"
        return databaseClient.sql(sql)
            .bind("name", inAdminLevelInsertVO.name)
            .then()
    }

    override fun updateLevel(inAdminLevelUpdateVO: InAdminLevelUpdateVO): Mono<Void> {
        val sql = "UPDATE RC_ROLE SET `NAME` = :name WHERE `UID` = :uid"
        return databaseClient.sql(sql)
            .bind("uid", inAdminLevelUpdateVO.uid)
            .bind("name", inAdminLevelUpdateVO.name)
            .then()
    }

    override fun deleteLevel(uid: Long): Mono<Void> {
        val sql = "DELETE FROM RC_ROLE WHERE `UID` = :uid"
        return databaseClient.sql(sql)
            .bind("uid", uid)
            .then()
    }

    override fun selectResourceList(): Flux<OutAdminResourceSelectVO> {
        val sql = "SELECT `UID`, `METHOD`, `URL_PATTERN`, `DESCRIPTION`, `CREATED_AT`, `UPDATED_AT` FROM RC_RESOURCE"
        return databaseClient.sql(sql)
            .map { row, _ ->
                OutAdminResourceSelectVO(
                    uid = row.get("UID", Long::class.java)

                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    method = row.get("METHOD", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    pattern = row.get("PATTERN", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    description = row.get("DESCRIPTION", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    createdAt = row.get("CREATED_AT", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    updatedAt = row.get("UPDATED_AT", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                )
            }
            .all()
    }

    override fun insertResource(inAdminResourceInsertVO: InAdminResourceInsertVO): Mono<Void> {
        val sql = "INSERT INTO RC_RESOURCE (`METHOD`, `URL_PATTERN`, `DESCRIPTION`) VALUES (:method, :pattern, :description)"
        return databaseClient.sql(sql)
            .bind("method", inAdminResourceInsertVO.method)
            .bind("pattern", inAdminResourceInsertVO.pattern)
            .bind("description", inAdminResourceInsertVO.description)
            .then()
    }

    override fun updateResource(inAdminResourceUpdateVO: InAdminResourceUpdateVO): Mono<Void> {
        val sql =
            "UPDATE RC_RESOURCE SET `METHOD` = :method, `URL_PATTERN` = :pattern, `DESCRIPTION` = :description WHERE `UID` = :uid"
        return databaseClient.sql(sql)
            .bind("uid", inAdminResourceUpdateVO.uid)
            .bind("method", inAdminResourceUpdateVO.method)
            .bind("pattern", inAdminResourceUpdateVO.pattern)
            .bind("description", inAdminResourceUpdateVO.description)
            .then()
    }

    override fun deleteResource(uid: Long): Mono<Void> {
        val sql = "DELETE FROM RC_RESOURCE WHERE `UID` = :uid"
        return databaseClient.sql(sql)
            .bind("uid", uid)
            .then()
    }

    override fun selectRoleList(): Flux<OutAdminRoleSelectVO> {
        val sql = """
            SELECT RRR.`RC_ROLE_UID`, RRR.`RC_RESOURCE_UID`, RRE.`DESCRIPTION` , RR.`NAME`, RRE.`URL_PATTERN`, RRE.`METHOD` , RRR.`CREATED_AT`, RRR.`UPDATED_AT`
            FROM RC_ROLE_RESOURCE RRR 
            JOIN RC_ROLE RR ON RRR.`RC_ROLE_UID` = RR.`UID` 
            JOIN RC_RESOURCE RRE ON RRR.`RC_RESOURCE_UID` = RRE.`UID`
        """
        return databaseClient.sql(sql)
            .map { row, _ ->
                OutAdminRoleSelectVO(
                    rcRoleUid = row.get("rc_role_uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    rcResourceUid = row.get("rc_resource_uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    roleName = row.get("name", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    roleResourcePattern = row.get("url_pattern", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    resourceDescription = row.get("description", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    resourceMethod = row.get("method", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    createdAt = row.get("created_at", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    updatedAt = row.get("updated_at", LocalDateTime::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    )
            }
            .all()
    }

    override fun insertRoleResource(inAdminRoleInsertVO: InAdminRoleInsertVO): Mono<Void> {
        val sql = "INSERT INTO RC_ROLE_RESOURCE (`RC_ROLE_UID`, `RC_RESOURCE_UID`) VALUES (:role_uid, :resource_uid)"
        return databaseClient.sql(sql)
            .bind("role_uid", inAdminRoleInsertVO.roleUid)
            .bind("resource_uid", inAdminRoleInsertVO.resourceUid)
            .then()
    }

    override fun updateRoleResource(inAdminRoleUpdateVO: InAdminRoleUpdateVO): Mono<Void> {
        val sql = """
            UPDATE 
                RC_ROLE_RESOURCE 
            SET 
                RC_ROLE_UID = :after_role_uid,
                RC_RESOURCE_UID = :after_resource_uid
            WHERE
                RC_ROLE_UID = :before_role_uid 
            AND
                RC_RESOURCE_UID = :before_resource_uid
            """.trimIndent()
        return databaseClient.sql(sql)
            .bind("before_role_uid", inAdminRoleUpdateVO.beforeRoleUid)
            .bind("before_resource_uid", inAdminRoleUpdateVO.beforeResourceUid)
            .bind("after_role_uid", inAdminRoleUpdateVO.afterRoleUid)
            .bind("after_resource_uid", inAdminRoleUpdateVO.afterResourceUid)
            .then()
    }

    override fun deleteRoleResource(inAdminRoleDeleteVO: InAdminRoleDeleteVO): Mono<Void> {
        val sql = """
            DELETE FROM
                RC_ROLE_RESOURCE 
            WHERE 
                `RC_ROLE_UID` = :role_uid 
            AND 
                `RC_RESOURCE_UID` = :resource_uid
            """.trimIndent()
        return databaseClient.sql(sql)
            .bind("role_uid", inAdminRoleDeleteVO.roleUid)
            .bind("resource_uid", inAdminRoleDeleteVO.resourceUid)
            .then()
    }
}