package com.devcjw.reactivecommunity.admin.model.entity

import java.time.LocalDateTime

data class OutAdminRoleSelectVO(
    val uid: Long,
    val levelUid: Long,
    val levelName: String,
    val resourceUid: Long,
    val resourceMethod: String,
    val resourcePattern: String,
    val resourceDescription: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
