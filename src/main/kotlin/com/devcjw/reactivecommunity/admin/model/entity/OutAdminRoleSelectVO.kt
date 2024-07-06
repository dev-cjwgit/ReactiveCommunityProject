package com.devcjw.reactivecommunity.admin.model.entity

import java.time.LocalDateTime

data class OutAdminRoleSelectVO(
    val rcRoleUid: Long,
    val rcResourceUid: Long,
    val roleName: String,
    val resourceMethod: String,
    val roleResourcePattern: String,
    val resourceDescription: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
