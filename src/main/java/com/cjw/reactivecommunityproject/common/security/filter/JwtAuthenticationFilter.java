package com.cjw.reactivecommunityproject.common.security.filter;

import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        extractJwtToken(request)
                .map(jwtService::getClaims)
                .ifPresent(securityAccessJwtVO -> {
                    SecurityContextHolder.getContext().setAuthentication(securityAccessJwtVO);
                });
        filterChain.doFilter(request, response);
    }

    private Optional<String> extractJwtToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.startsWith(header, "Bearer ")) {
            return Optional.of(header.substring(7)); // "Bearer " 부분을 잘라낸 JWT 토큰 반환
        }
        return Optional.empty();
    }

}
