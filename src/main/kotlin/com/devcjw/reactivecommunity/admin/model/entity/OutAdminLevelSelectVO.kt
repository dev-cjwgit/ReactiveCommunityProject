package com.devcjw.reactivecommunity.admin.model.entity

import java.time.LocalDateTime

data class OutAdminLevelSelectVO(
        val uid: Long,
        val name: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
)
