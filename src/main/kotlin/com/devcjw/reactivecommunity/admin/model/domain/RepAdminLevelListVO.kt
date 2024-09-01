package com.devcjw.reactivecommunity.admin.model.domain

import java.time.LocalDateTime

data class RepAdminLevelListVO(
        val uid: Long,
        val name: String,
        val createdUtcAt: LocalDateTime,
        val updatedUtcAt: LocalDateTime,
)
