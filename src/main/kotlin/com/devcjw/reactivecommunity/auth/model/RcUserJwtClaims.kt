package com.devcjw.reactivecommunity.auth.model

data class RcUserJwtClaims(
    val uid: String,
    val level: Long
)
