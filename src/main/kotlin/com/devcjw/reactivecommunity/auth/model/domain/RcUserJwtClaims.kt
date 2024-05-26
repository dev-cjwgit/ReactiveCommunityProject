package com.devcjw.reactivecommunity.auth.model.domain

data class RcUserJwtClaims(
    val uid: String,
    val level: Long
)
