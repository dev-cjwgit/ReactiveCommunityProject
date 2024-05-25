package com.devcjw.reactivecommunity.auth.model.domain

data class AuthRepTokenVO(
    val accessToken: String,
    val refreshToken: String,
)
