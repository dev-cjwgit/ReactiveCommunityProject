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
        val sql = "SELECT UID, NAME, CREATED_AT, UPDATED_AT FROM RC_USER_LEVEL"
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
        val sql = "INSERT INTO RC_USER_LEVEL (NAME) VALUES (:name)"
        return databaseClient.sql(sql)
            .bind("name", inAdminLevelInsertVO.name)
            .then()
    }

    override fun updateLevel(inAdminLevelUpdateVO: InAdminLevelUpdateVO): Mono<Void> {
        val sql = "UPDATE RC_USER_LEVEL SET NAME = :name WHERE UID = :uid"
        return databaseClient.sql(sql)
            .bind("uid", inAdminLevelUpdateVO.uid)
            .bind("name", inAdminLevelUpdateVO.name)
            .then()
    }

    override fun deleteLevel(uid: Long): Mono<Void> {
        val sql = "DELETE FROM RC_USER_LEVEL WHERE UID = :uid"
        return databaseClient.sql(sql)
            .bind("uid", uid)
            .then()
    }

    override fun selectResourceList(): Flux<OutAdminResourceSelectVO> {
        val sql = "SELECT UID, METHOD, PATTERN, DESCRIPTION, CREATED_AT, UPDATED_AT FROM RC_USER_RESOURCE"
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
        val sql = "INSERT INTO RC_USER_RESOURCE (METHOD, PATTERN, DESCRIPTION) VALUES (:method, :pattern, :description)"
        return databaseClient.sql(sql)
            .bind("method", inAdminResourceInsertVO.method)
            .bind("pattern", inAdminResourceInsertVO.pattern)
            .bind("description", inAdminResourceInsertVO.description)
            .then()
    }

    override fun updateResource(inAdminResourceUpdateVO: InAdminResourceUpdateVO): Mono<Void> {
        val sql =
            "UPDATE RC_USER_RESOURCE SET METHOD = :method, PATTERN = :pattern, DESCRIPTION = :description WHERE UID = :uid"
        return databaseClient.sql(sql)
            .bind("uid", inAdminResourceUpdateVO.uid)
            .bind("method", inAdminResourceUpdateVO.method)
            .bind("pattern", inAdminResourceUpdateVO.pattern)
            .bind("description", inAdminResourceUpdateVO.description)
            .then()
    }

    override fun deleteResource(uid: Long): Mono<Void> {
        val sql = "DELETE FROM RC_USER_RESOURCE WHERE UID = :uid"
        return databaseClient.sql(sql)
            .bind("uid", uid)
            .then()
    }

    override fun selectRoleList(): Flux<OutAdminRoleSelectVO> {
        val sql = """
            SELECT RRR.UID, RRR.LEVEL_UID, RRR.RESOURCE_UID, RUR.DESCRIPTION , RUL.NAME, RUR.PATTERN, RUR.METHOD , RRR.CREATED_AT, RRR.UPDATED_AT
            FROM RD_ROLE_RESOURCE RRR 
            JOIN RC_USER_LEVEL RUL ON RRR.LEVEL_UID = RUL.UID 
            JOIN RC_USER_RESOURCE RUR ON RRR.RESOURCE_UID = RUR.UID
        """
        return databaseClient.sql(sql)
            .map { row, _ ->
                OutAdminRoleSelectVO(
                    uid = row.get("uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    levelUid = row.get("level_uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    resourceUid = row.get("resource_uid", Long::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    levelName = row.get("name", String::class.java)
                        ?: throw RcException(RcErrorMessage.R2DBC_MAPPING_EXCEPTION),
                    resourcePattern = row.get("pattern", String::class.java)
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

    override fun insertRole(inAdminRoleInsertVO: InAdminRoleInsertVO): Mono<Void> {
        val sql = "INSERT INTO RD_ROLE_RESOURCE (LEVEL_UID, RESOURCE_UID) VALUES (:levelUid, :resourceUid)"
        return databaseClient.sql(sql)
            .bind("levelUid", inAdminRoleInsertVO.levelUid)
            .bind("resourceUid", inAdminRoleInsertVO.resourceUid)
            .then()
    }

    override fun updateRole(inAdminRoleUpdateVO: InAdminRoleUpdateVO): Mono<Void> {
        val sql = "UPDATE RD_ROLE_RESOURCE SET LEVEL_UID = :levelUid, RESOURCE_UID = :resourceUid WHERE UID = :uid"
        return databaseClient.sql(sql)
            .bind("uid", inAdminRoleUpdateVO.uid)
            .bind("levelUid", inAdminRoleUpdateVO.levelUid)
            .bind("resourceUid", inAdminRoleUpdateVO.resourceUid)
            .then()
    }

    override fun deleteRole(uid: Long): Mono<Void> {
        val sql = "DELETE FROM RD_ROLE_RESOURCE WHERE UID = :uid"
        return databaseClient.sql(sql)
            .bind("uid", uid)
            .then()
    }
}