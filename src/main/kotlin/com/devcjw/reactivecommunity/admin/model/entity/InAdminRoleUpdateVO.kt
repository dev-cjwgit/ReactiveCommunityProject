package com.devcjw.reactivecommunity.admin.model.entity

data class InAdminRoleUpdateVO(
        val beforeRoleUid: Long,
        val beforeResourceUid: Long,
        val afterRoleUid: Long,
        val afterResourceUid: Long,
)
