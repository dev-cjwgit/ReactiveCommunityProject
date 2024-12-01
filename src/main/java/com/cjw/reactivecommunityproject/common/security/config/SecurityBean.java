package com.cjw.reactivecommunityproject.common.security.config;

import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityBean {
    private final RcProperties rcProperties;

    private static final String[] ALLOW_ORIGINS_LIST = {"*"};
    private static final String[] ALLOW_METHODS_LIST = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};
    private static final String[] ALLOW_HEADERS_LIST = {"Content-Type", "Authorization"};

    @Bean
    public SecretKey getSecretKey(){
        var secretKey = rcProperties.jwt().secretKey();
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(ALLOW_ORIGINS_LIST));
        configuration.setAllowedMethods(List.of(ALLOW_METHODS_LIST));
        configuration.setAllowedHeaders(List.of(ALLOW_HEADERS_LIST));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
