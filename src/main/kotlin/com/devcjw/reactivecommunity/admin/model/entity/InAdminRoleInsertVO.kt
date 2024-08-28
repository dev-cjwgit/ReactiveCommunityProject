package com.devcjw.reactivecommunity.admin.model.entity

data class InAdminRoleInsertVO(
        val roleUid: Long,
        val resourceUid: Long,
        val createdUserUid: String
)
