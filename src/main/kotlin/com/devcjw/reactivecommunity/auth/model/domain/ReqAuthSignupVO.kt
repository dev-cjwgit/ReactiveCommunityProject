package com.devcjw.reactivecommunity.auth.model.domain

data class ReqAuthSignupVO(
    val email: String,
    val password: String,
    val name: String,
    val nickname: String,
)
