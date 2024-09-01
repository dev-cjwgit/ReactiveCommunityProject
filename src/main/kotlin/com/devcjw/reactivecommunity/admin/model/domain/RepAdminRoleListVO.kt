package com.devcjw.reactivecommunity.admin.model.domain

import java.time.LocalDateTime

data class RepAdminRoleListVO(
        val levelUid: Long,
        val levelName: String,
        val resourceUid: Long,
        val resourcePattern: String,
        val resourceDescription: String,
        val createdUtcAt: LocalDateTime,
        val updatedUtcAt: LocalDateTime,
)
