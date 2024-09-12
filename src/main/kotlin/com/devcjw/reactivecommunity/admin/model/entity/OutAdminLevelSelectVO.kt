package com.devcjw.reactivecommunity.admin.model.entity

import java.time.ZonedDateTime

data class OutAdminLevelSelectVO(
        val uid: Long,
        val name: String,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
