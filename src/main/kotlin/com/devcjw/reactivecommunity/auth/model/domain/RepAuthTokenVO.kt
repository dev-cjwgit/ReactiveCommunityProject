package com.devcjw.reactivecommunity.auth.model.domain

data class RepAuthTokenVO(
    val accessToken: String,
    val refreshToken: String,
)
