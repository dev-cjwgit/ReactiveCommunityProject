package com.devcjw.reactivecommunity.auth.model

data class RcUserJwtClaims(
    private val uid: String,
    private val level: Long
)
