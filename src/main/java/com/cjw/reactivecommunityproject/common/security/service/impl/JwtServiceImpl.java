package com.cjw.reactivecommunityproject.common.security.service.impl;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.security.exception.SecurityErrorMessage;
import com.cjw.reactivecommunityproject.common.security.exception.SecurityException;
import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwt;
import com.cjw.reactivecommunityproject.common.security.model.SecurityJwtPayload;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final CacheCustomService cacheCustomService;


    private Long getTokenExpiresByCommonEnvCode(String tokenType) {
        var envcode = cacheCustomService.getCustomCommonEnvCode(StringUtils.join("rc.jwt.", tokenType));
        if (envcode == null) {
            throw new AuthException(RcCommonErrorMessage.NOT_FOUND_ENV_CODE);
        }
        if (!NumberUtils.isDigits(envcode.getValue())) {
            throw new AuthException(RcCommonErrorMessage.INVALID_ENV_CODE);
        }

        return NumberUtils.toLong(envcode.getValue());
    }

    private Long getAccessTokenExpiresByCommonEnvCode() {
        return getTokenExpiresByCommonEnvCode("access.token.expires.minutes");
    }

    private Long getRefreshTokenExpiresByCommonEnvCode() {
        return getTokenExpiresByCommonEnvCode("refresh.token.expires.minutes");
    }

    private String getSecretKeyByCommonEnvCode() {
        var envcode = cacheCustomService.getCustomCommonEnvCode("rc.jwt.secret.key");
        if (envcode == null) {
            throw new AuthException(RcCommonErrorMessage.NOT_FOUND_ENV_CODE);
        }
        if (StringUtils.isBlank(envcode.getValue())) {
            throw new AuthException(RcCommonErrorMessage.INVALID_ENV_CODE);
        }

        return envcode.getValue();
    }

    private String getUserUidByJwtPayload(String token) {
        String[] parts = StringUtils.split(token, ".");
        if (parts.length != 3) {
            throw new SecurityException(SecurityErrorMessage.INVALID_TOKEN_STRUCT);
        }

        String payload = parts[1];
        byte[] decodedPayload = Base64.getUrlDecoder().decode(payload);
        try {
            var jwtPayloadVO = objectMapper.readValue(new String(decodedPayload), SecurityJwtPayload.class);
            return jwtPayloadVO.sub();
        } catch (JsonProcessingException ex) {
            throw new SecurityException(SecurityErrorMessage.INVALID_TOKEN_PAYLOAD);
        }
    }

    private SecretKey getSecretKey(String userUid) {
        String salt;
        if (StringUtils.isBlank(userUid)) {
            salt = "";
        } else {
            salt = String.valueOf(redisTemplate.opsForValue().get(userUid + ".salt"));
        }
        if (StringUtils.equalsIgnoreCase(salt, "null")) {
            throw new SecurityException(SecurityErrorMessage.NOT_FOUND_USER_SALT);
        }

        var secretKey = this.getSecretKeyByCommonEnvCode() + "." + salt;
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String createToken(SecurityAccessJwt securityAccessJwt, Long expiresMinutes) {
        return Jwts.builder()
                .subject(securityAccessJwt.userUid())
                .claim("role", securityAccessJwt.roleUid())

                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expiresMinutes))

                .signWith(this.getSecretKey(securityAccessJwt.userUid()))
                .compact();
    }


    @Override
    public String createAccessToken(SecurityAccessJwt securityAccessJwt) {
        return createToken(securityAccessJwt, this.getAccessTokenExpiresByCommonEnvCode() * 1000 * 60);
    }

    @Override
    public String createRefreshToken(String userUid) {
        return createToken(SecurityAccessJwt.builder()
                .userUid(userUid)
                .roleUid(null)
                .build(), this.getRefreshTokenExpiresByCommonEnvCode() * 1000 * 60);
    }

    @Override
    public Boolean validateToken(String token) {
        return this.getClaims(token) != null;
    }

    @Override
    public SecurityAccessJwt getClaims(String token) {
        try {
            var userUid = this.getUserUidByJwtPayload(token);
            var claims = Jwts.parser()
                    .verifyWith(this.getSecretKey(userUid))
                    .build()
                    .parseSignedClaims(token);
            return SecurityAccessJwt.builder()
                    .userUid(
                            claims.getPayload().getSubject()
                    )
                    .roleUid(
                            claims.getPayload().get("role", Integer.class)
                    )
                    .build(); // 토큰이 유효함
        } catch (SignatureException e) {
            log.warn("JwtServiceImpl.getClaims(): Invalid Jwt Signature");
        } catch (MalformedJwtException e) {
            log.warn("JwtServiceImpl.getClaims(): Invalid Jwt Token");
        } catch (ExpiredJwtException e) {
            log.warn("JwtServiceImpl.getClaims(): Expires Jwt Token");
        } catch (UnsupportedJwtException e) {
            log.warn("JwtServiceImpl.getClaims(): Unsupported Jwt Token");
        } catch (IllegalArgumentException e) {
            log.warn("JwtServiceImpl.getClaims(): Jwt Token is empty");
        } catch (RcBaseException e) {
            log.warn("JwtServiceImpl.getClaims(): {}", e.getErrorMessage());
        } catch (Exception e) {
            log.error("JwtServiceImpl.getClaims(): {}", e.getMessage(), e);
        }
        return null;
    }
}
