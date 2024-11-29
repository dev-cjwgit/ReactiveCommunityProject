package com.cjw.reactivecommunityproject.common.security.service;

import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwtVO;

public interface JwtService {
    String createAccessToken(SecurityAccessJwtVO securityAccessJwtVO);

    String createRefreshToken();

    Boolean validateToken(String token);

    SecurityAccessJwtVO getClaims(String token);
}
