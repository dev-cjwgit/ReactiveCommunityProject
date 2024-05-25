package com.devcjw.reactivecommunity.auth.model.domain

data class AuthReqReissueDTO(
    val email: String,
    val refreshToken: String,
)
