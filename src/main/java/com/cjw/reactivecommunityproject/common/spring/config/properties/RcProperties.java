package com.cjw.reactivecommunityproject.common.spring.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;


@ConfigurationProperties(prefix = "rc")
public record RcProperties(
        ConfigProperties config,
        JwtProperties jwt,
        CacheProperties cache
) {
    public record ConfigProperties(
            String defaultRegion,
            String defaultLanguage
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