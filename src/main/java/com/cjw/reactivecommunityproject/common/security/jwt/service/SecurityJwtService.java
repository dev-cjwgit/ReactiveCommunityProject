package com.cjw.reactivecommunityproject.common.security.jwt.service;

import com.cjw.reactivecommunityproject.common.security.jwt.model.SecurityJwtAccessVO;

public interface SecurityJwtService {
    String createAccessToken(SecurityJwtAccessVO securityJwtAccessVO);

    String createRefreshToken(String userUid);

    Boolean validateToken(String token);

    SecurityJwtAccessVO getClaims(String token);
}
