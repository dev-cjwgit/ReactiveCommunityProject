package com.devcjw.reactivecommunity.admin.model.domain

import java.time.ZonedDateTime

data class RepAdminRoleListVO(
        val levelUid: Long,
        val levelName: String,
        val resourceUid: Long,
        val resourcePattern: String,
        val resourceDescription: String,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
