package com.devcjw.reactivecommunity.auth.model.domain

data class ReqAuthCheckVO(
        val accessToken: String,
        val path: String,
)
