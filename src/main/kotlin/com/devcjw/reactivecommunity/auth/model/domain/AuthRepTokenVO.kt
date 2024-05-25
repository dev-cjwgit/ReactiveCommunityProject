package com.devcjw.reactivecommunity.auth.model.domain

import lombok.Builder

data class AuthRepTokenVO(
    val accessToken: String,
    val refreshToken: String
)
