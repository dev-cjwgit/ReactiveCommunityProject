package com.devcjw.reactivecommunity.auth.service.impl

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.auth.service.JwtService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.annotation.PostConstruct
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Slf4j
@Service
@RequiredArgsConstructor
class JwtServiceImpl : JwtService {
    private lateinit var jwtKey: SecretKey

    @Value("\${rc.jwt.access-token-expires-minute}")
    private val accessTokenExpiresMinute: Long? = null

    @Value("\${rc.jwt.refresh-token-expires-day}")
    private val refreshTokenExpiresDay: Long? = null

    @Value("\${rc.jwt.secret-key}")
    private val secretKey: String? = null

    @PostConstruct
    fun init() {
        jwtKey = SecretKeySpec(secretKey?.toByteArray(), SignatureAlgorithm.HS256.jcaName)
    }

    private fun getClaimsFormToken(token: String) = Jwts.parserBuilder()
        .setSigningKey(jwtKey)
        .build()
        .parseClaimsJws(token)

    private fun createToken(uid: String, level: Long, expiresTime: Long): String {
        val claims = Jwts.claims().apply {
            this["uid"] = uid
            this["level"] = level
        }

        val now = Date()
        val expires = Date(now.time + expiresTime)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expires)
            .signWith(jwtKey)
            .compact()
    }

    override fun createAccessToken(uid: String, level: Long): String {
        return createToken(uid, level, (accessTokenExpiresMinute?.times(1000) ?: 0) * 60)
    }

    override fun createRefreshToken(uid: String, level: Long): String {
        return createToken(uid, level, (refreshTokenExpiresDay?.times(1000) ?: 0) * 60 * 60 * 24)
    }

    override fun validateToken(token: String): Boolean {
        return try {
            val claims = getClaimsFormToken(token)
            !claims.body.expiration.before(Date())
        } catch (ex: Exception) {
            // TODO Logging 필요
            false
        }
    }

    override fun getRcUser(token: String): RcUserJwtClaims {
        val claims = getClaimsFormToken(token)
        return RcUserJwtClaims(claims.body["uid"].toString(), claims.body["level"].toString().toLong())
    }


}