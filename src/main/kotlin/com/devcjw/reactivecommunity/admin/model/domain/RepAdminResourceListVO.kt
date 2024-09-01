package com.devcjw.reactivecommunity.admin.model.domain

import java.time.LocalDateTime

data class RepAdminResourceListVO(
        val uid: Long,
        val method: String,
        val pattern: String,
        val description: String,
        val createdUtcAt: LocalDateTime,
        val updatedUtcAt: LocalDateTime,
)
