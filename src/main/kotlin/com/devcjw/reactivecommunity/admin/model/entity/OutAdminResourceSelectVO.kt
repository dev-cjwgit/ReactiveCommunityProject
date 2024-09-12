package com.devcjw.reactivecommunity.admin.model.entity

import java.time.ZonedDateTime

data class OutAdminResourceSelectVO(
        val uid: Long,
        val method: String,
        val urlPattern: String,
        val description: String,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
