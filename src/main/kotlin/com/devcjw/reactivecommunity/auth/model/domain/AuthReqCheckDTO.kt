package com.devcjw.reactivecommunity.auth.model.domain

data class AuthReqCheckDTO(
    val accessToken: String,
    val path: String,
)
