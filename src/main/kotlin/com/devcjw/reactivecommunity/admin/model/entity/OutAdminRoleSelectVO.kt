package com.devcjw.reactivecommunity.admin.model.entity

import java.time.ZonedDateTime

data class OutAdminRoleSelectVO(
        val roleUid: Long,
        val resourceUid: Long,
        val roleName: String,
        val resourceMethod: String,
        val urlPattern: String,
        val resourceDescription: String,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
