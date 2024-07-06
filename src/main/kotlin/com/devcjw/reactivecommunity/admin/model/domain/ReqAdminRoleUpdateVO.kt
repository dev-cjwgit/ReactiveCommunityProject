package com.devcjw.reactivecommunity.admin.model.domain

data class ReqAdminRoleUpdateVO(
    val beforeRoleUid: Long,
    val beforeResourceUid: Long,
    val afterRoleUid: Long,
    val afterResourceUid: Long,
)
