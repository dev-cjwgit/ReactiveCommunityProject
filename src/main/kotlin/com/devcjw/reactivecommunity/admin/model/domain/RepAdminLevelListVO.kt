package com.devcjw.reactivecommunity.admin.model.domain

import java.time.ZonedDateTime

data class RepAdminLevelListVO(
        val uid: Long,
        val name: String,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
