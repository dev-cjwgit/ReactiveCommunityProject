package com.devcjw.reactivecommunity.common.model.entity

import com.devcjw.reactivecommunity.common.model.EnabledStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table("rc_region")
data class RcRegionEntity(
    @Id val region: String,
    val description: String,
    val enabled: EnabledStatus,
    val createdUserUid: String? = null,
    val createdUtcAt: ZonedDateTime,
    val updatedUserUid: String? = null,
    val updatedUtcAt: ZonedDateTime
)
