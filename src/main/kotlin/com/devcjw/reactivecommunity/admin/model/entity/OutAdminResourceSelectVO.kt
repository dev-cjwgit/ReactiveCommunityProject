package com.devcjw.reactivecommunity.admin.model.entity

import java.time.LocalDateTime

data class OutAdminResourceSelectVO(
    val uid: Long,
    val method: String,
    val pattern: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
