package com.devcjw.reactivecommunity.auth.service

import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims

interface JwtService {
    fun createAccessToken(uid: String, level: Long): String

    // TODO: Refresh Token 은 payload 가 필요 없다
    fun createRefreshToken(uid: String, level: Long): String

    fun validateToken(token: String): Boolean

    fun getRcUser(token: String): RcUserJwtClaims
}