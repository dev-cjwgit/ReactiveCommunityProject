package com.cjw.reactivecommunityproject.common.spring.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "rc")
public record RcProperties(
        ConfigProperties config,
        JwtProperties jwt,
        CacheProperties cache
) {
    public record ConfigProperties(
            String defaultRegion
    ) {
    }

    public record JwtProperties(
            long accessTokenExpiresMinutes,
            long refreshTokenExpiresMinutes,
            String secretKey
    ) {
    }

    public record CacheProperties(
            int expiresMinutes
    ) {
    }
}