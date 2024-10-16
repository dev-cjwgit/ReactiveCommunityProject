package com.devcjw.reactivecommunity.common.model.entity

import com.devcjw.reactivecommunity.common.model.EnabledStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime


@Table("rc_file")
data class RcFileEntity(
    @Id val uid: String,
    val path: String,
    val name: String,
    val size: Int,
    val md5: String,
    val enabled: EnabledStatus,
    val createdUtcAt: ZonedDateTime,
    val updatedUtcAt: ZonedDateTime
)