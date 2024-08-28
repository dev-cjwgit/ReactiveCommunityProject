package com.devcjw.reactivecommunity.admin.model.entity

data class InAdminResourceUpdateVO(
        val uid: Long,
        val method: String,
        val pattern: String,
        val description: String,
)
