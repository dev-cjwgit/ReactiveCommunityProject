package com.cjw.reactivecommunityproject.common.security.service;

import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwt;

public interface JwtService {
    String createAccessToken(SecurityAccessJwt securityAccessJwt);

    String createRefreshToken(String userUid);

    Boolean validateToken(String token);

    SecurityAccessJwt getClaims(String token);
}
