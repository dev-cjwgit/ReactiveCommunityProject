package com.devcjw.reactivecommunity.admin.model.domain

data class RepAdminResourceUpdateVO(
    val uid: Long,
    val method: String,
    val pattern: String,
    val description: String,
)
