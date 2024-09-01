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
        val sql = "SELECT `uid`, `name`, `created_utc_at`, `updated_utc_at` FROM rc_role"
        return databaseClient.sql(sql)
                .map { row, _ ->
                    OutAdminLevelSelectVO(
                            uid = row.get("uid", Long::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            name = row.get("name", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            createdUtcAt = row.get("created_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            updatedUtcAt = row.get("updated_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION)
                    )
                }
                .all()
    }

    override fun insertLevel(inAdminLevelInsertVO: InAdminLevelInsertVO): Mono<Void> {
        val sql = "INSERT INTO rc_role (`name`, `created_user_uid`) VALUES (:name, :created_user_uid)"
        return databaseClient.sql(sql)
                .bind("name", inAdminLevelInsertVO.name)
                .bind("created_user_uid", inAdminLevelInsertVO.createdUserUid)
                .then()
    }

    override fun updateLevel(inAdminLevelUpdateVO: InAdminLevelUpdateVO): Mono<Void> {
        val sql = "UPDATE rc_role SET `name` = :name WHERE `uid` = :uid"
        return databaseClient.sql(sql)
                .bind("uid", inAdminLevelUpdateVO.uid)
                .bind("name", inAdminLevelUpdateVO.name)
                .then()
    }

    override fun deleteLevel(uid: Long): Mono<Void> {
        val sql = "DELETE FROM rc_role WHERE `uid` = :uid"
        return databaseClient.sql(sql)
                .bind("uid", uid)
                .then()
    }

    override fun selectResourceList(): Flux<OutAdminResourceSelectVO> {
        val sql = "SELECT `uid`, `method`, `url_pattern`, `description`, `created_utc_at`, `updated_utc_at` FROM rc_resource"
        return databaseClient.sql(sql)
                .map { row, _ ->
                    OutAdminResourceSelectVO(
                            uid = row.get("uid", Long::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            method = row.get("method", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            urlPattern = row.get("url_pattern", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            description = row.get("description", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            createdUtcAt = row.get("created_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            updatedUtcAt = row.get("updated_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    )
                }
                .all()
    }

    override fun insertResource(inAdminResourceInsertVO: InAdminResourceInsertVO): Mono<Void> {
        val sql = "INSERT INTO rc_resource (`method`, `url_pattern`, `description`, `created_user_uid`) VALUES (:method, :pattern, :description,:created_user_uid)"
        return databaseClient.sql(sql)
                .bind("method", inAdminResourceInsertVO.method)
                .bind("pattern", inAdminResourceInsertVO.pattern)
                .bind("description", inAdminResourceInsertVO.description)
                .bind("created_user_uid", inAdminResourceInsertVO.createdUserUid)
                .then()
    }

    override fun updateResource(inAdminResourceUpdateVO: InAdminResourceUpdateVO): Mono<Void> {
        val sql =
                "UPDATE rc_resource SET `method` = :method, `url_pattern` = :pattern, `description` = :description WHERE `uid` = :uid"
        return databaseClient.sql(sql)
                .bind("uid", inAdminResourceUpdateVO.uid)
                .bind("method", inAdminResourceUpdateVO.method)
                .bind("pattern", inAdminResourceUpdateVO.pattern)
                .bind("description", inAdminResourceUpdateVO.description)
                .then()
    }

    override fun deleteResource(uid: Long): Mono<Void> {
        val sql = "DELETE FROM rc_resource WHERE `uid` = :uid"
        return databaseClient.sql(sql)
                .bind("uid", uid)
                .then()
    }

    override fun selectRoleList(): Flux<OutAdminRoleSelectVO> {
        val sql = """
            SELECT RRR.`role_uid`, RRR.`resource_uid`, RRE.`description` , RR.`name`, RRE.`url_pattern`, RRE.`method` , RRR.`created_utc_at`, RRR.`updated_utc_at`
            FROM rc_role_resource RRR 
            JOIN rc_role RR ON RRR.`role_uid` = RR.`uid` 
            JOIN rc_resource RRE ON RRR.`resource_uid` = RRE.`uid`
        """
        return databaseClient.sql(sql)
                .map { row, _ ->
                    OutAdminRoleSelectVO(
                            roleUid = row.get("role_uid", Long::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            resourceUid = row.get("resource_uid", Long::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            roleName = row.get("name", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            urlPattern = row.get("url_pattern", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            resourceDescription = row.get("description", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            resourceMethod = row.get("method", String::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            createdUtcAt = row.get("created_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                            updatedUtcAt = row.get("updated_utc_at", LocalDateTime::class.java)
                                    ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    )
                }
                .all()
    }

    override fun insertRoleResource(inAdminRoleInsertVO: InAdminRoleInsertVO): Mono<Void> {
        val sql = "INSERT INTO rc_role_resource (`role_uid`, `resource_uid`,`created_user_uid`) VALUES (:role_uid, :resource_uid, :created_user_uid)"
        return databaseClient.sql(sql)
                .bind("role_uid", inAdminRoleInsertVO.roleUid)
                .bind("resource_uid", inAdminRoleInsertVO.resourceUid)
                .bind("created_user_uid", inAdminRoleInsertVO.createdUserUid)
                .then()
    }

    override fun updateRoleResource(inAdminRoleUpdateVO: InAdminRoleUpdateVO): Mono<Void> {
        val sql = """
            UPDATE 
                rc_role_resource RRR
            SET 
                RRR.`role_uid` = :after_role_uid,
                RRR.`resource_uid` = :after_resource_uid
            WHERE
                RRR.`role_uid` = :before_role_uid 
            AND
                RRR.`resource_uid` = :before_resource_uid
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
                rc_role_resource RRR 
            WHERE 
                RRR.`role_uid` = :role_uid 
            AND 
                RRR.`resource_uid` = :resource_uid
            """.trimIndent()
        return databaseClient.sql(sql)
                .bind("role_uid", inAdminRoleDeleteVO.roleUid)
                .bind("resource_uid", inAdminRoleDeleteVO.resourceUid)
                .then()
    }
}