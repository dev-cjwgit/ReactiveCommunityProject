package com.cjw.reactivecommunityproject.common.security.service.impl;

import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwtVO;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    // TODO: 하드코딩 제거
    private static final String secretKey = "SpringSecurityKey_P@ssword_http://Spring.io";
    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());


    // TODO: 하드코딩 제거
    private final long expiresTime = 500000;

    private String createToken(SecurityAccessJwtVO securityAccessJwtVO) {
        return Jwts.builder()
                .subject(securityAccessJwtVO.userUid())
                .claim("role", securityAccessJwtVO.roleUid())

                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expiresTime))

                .signWith(key)
                .compact();
    }

    @Override
    public String createAccessToken(SecurityAccessJwtVO securityAccessJwtVO) {
        return createToken(securityAccessJwtVO);
    }

    @Override
    public String createRefreshToken() {
        return createToken(SecurityAccessJwtVO.builder()
                .userUid(null)
                .roleUid(null)
                .build());
    }

    @Override
    public Boolean validateToken(String token) {
        return this.getClaims(token) != null;
    }

    @Override
    public SecurityAccessJwtVO getClaims(String token) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return SecurityAccessJwtVO.builder()
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
        } catch (Exception e) {
            log.error("JwtServiceImpl.getClaims(): {}", e.getMessage(), e);
        }
        return null;
    }
}
