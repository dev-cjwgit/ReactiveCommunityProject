package com.cjw.reactivecommunityproject.common.security.jwt.service.impl;

import com.cjw.reactivecommunityproject.common.exception.model.RcBaseException;
import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.security.exception.SecurityErrorMessage;
import com.cjw.reactivecommunityproject.common.security.exception.SecurityException;
import com.cjw.reactivecommunityproject.common.security.jwt.model.SecurityJwtAccessVO;
import com.cjw.reactivecommunityproject.common.security.jwt.model.SecurityJwtPayloadVO;
import com.cjw.reactivecommunityproject.common.security.jwt.service.SecurityJwtService;
import com.cjw.reactivecommunityproject.common.spring.util.EnvCodeUtils;
import com.cjw.reactivecommunityproject.server.cache.info.custom.service.CacheInfoCustomService;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityJwtServiceImpl implements SecurityJwtService {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final CacheInfoCustomService cacheInfoCustomService;


    private Integer getTokenExpiresByCommonEnvCode(String tokenType) {
        var envCode = EnvCodeUtils.convertEnvCodeByValue(cacheInfoCustomService.getCommonEnvCode(StringUtils.join("rc.jwt.", tokenType)), Integer.class);
        if (envCode == null) {
            throw new AuthException(RcCommonErrorMessage.NOT_FOUND_ENV_CODE);
        }

        return envCode;
    }

    private Integer getAccessTokenExpiresByCommonEnvCode() {
        return getTokenExpiresByCommonEnvCode("access.token.expires.minutes");
    }

    private Integer getRefreshTokenExpiresByCommonEnvCode() {
        return getTokenExpiresByCommonEnvCode("refresh.token.expires.minutes");
    }

    private String getSecretKeyByCommonEnvCode() {
        var envcode = EnvCodeUtils.convertEnvCodeByValue(cacheInfoCustomService.getCommonEnvCode("rc.jwt.secret.key"), String.class);
        if (envcode == null) {
            throw new AuthException(RcCommonErrorMessage.NOT_FOUND_ENV_CODE);
        }
        if (StringUtils.isBlank(envcode)) {
            throw new AuthException(RcCommonErrorMessage.INVALID_ENV_CODE);
        }

        return envcode;
    }

    private String getUserUidByJwtPayload(String token) {
        String[] parts = StringUtils.split(token, ".");
        if (parts.length != 3) {
            throw new SecurityException(SecurityErrorMessage.INVALID_TOKEN_STRUCT);
        }

        String payload = parts[1];
        byte[] decodedPayload = Base64.getUrlDecoder().decode(payload);
        try {
            var jwtPayloadVO = objectMapper.readValue(new String(decodedPayload), SecurityJwtPayloadVO.class);
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
        if (Strings.CI.equals(salt, "null")) {
            throw new SecurityException(SecurityErrorMessage.NOT_FOUND_USER_SALT);
        }

        var secretKey = this.getSecretKeyByCommonEnvCode() + "." + salt;
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String createToken(SecurityJwtAccessVO securityJwtAccessVO, Long expiresMinutes) {
        return Jwts.builder()
                .subject(securityJwtAccessVO.userUid())
                .claim("role", securityJwtAccessVO.roleUid())

                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expiresMinutes))

                .signWith(this.getSecretKey(securityJwtAccessVO.userUid()))
                .compact();
    }


    @Override
    public String createAccessToken(SecurityJwtAccessVO securityJwtAccessVO) {
        return createToken(securityJwtAccessVO, this.getAccessTokenExpiresByCommonEnvCode() * 1000 * 60L);
    }

    @Override
    public String createRefreshToken(String userUid) {
        return createToken(SecurityJwtAccessVO.builder()
                .userUid(userUid)
                .roleUid(null)
                .build(), this.getRefreshTokenExpiresByCommonEnvCode() * 1000 * 60L);
    }

    @Override
    public Boolean validateToken(String token) {
        return this.getClaims(token) != null;
    }

    @Override
    public SecurityJwtAccessVO getClaims(String token) {
        try {
            var userUid = this.getUserUidByJwtPayload(token);
            var claims = Jwts.parser()
                    .verifyWith(this.getSecretKey(userUid))
                    .build()
                    .parseSignedClaims(token);
            return SecurityJwtAccessVO.builder()
                    .userUid(claims.getPayload().getSubject())
                    .roleUid(claims.getPayload().get("role", Integer.class))
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
