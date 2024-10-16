package com.devcjw.reactivecommunity.common.model.entity

import com.devcjw.reactivecommunity.common.model.EnabledStatus
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table("rc_role_resource")
data class RcRoleResourceEntity(
    val roleUid: Byte,
    val resourceUid: Long,
    val enabled: EnabledStatus,
    val createdUserUid: String? = null,
    val createdUtcAt: ZonedDateTime,
    val updatedUserUid: String? = null,
    val updatedUtcAt: ZonedDateTime
)