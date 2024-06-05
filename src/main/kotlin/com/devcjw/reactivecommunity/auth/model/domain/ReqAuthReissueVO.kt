package com.devcjw.reactivecommunity.auth.model.domain

data class ReqAuthReissueVO(
    val email: String,
    val refreshToken: String,
)
