package com.cjw.reactivecommunityproject.common.security.filter.jwt;

import com.cjw.reactivecommunityproject.common.security.exception.SecurityErrorMessage;
import com.cjw.reactivecommunityproject.common.security.exception.SecurityException;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request
            , @NonNull HttpServletResponse response
            , @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            var token = extractJwtToken(request);
            if (StringUtils.isBlank(token)) {
                throw new SecurityException(SecurityErrorMessage.NOT_FOUND_TOKEN);
            }
            // 해당 메소드에서 모든 예외 처리를 수행하고 null 만 반환
            var claims = jwtService.getClaims(token);

            SecurityContextHolder.getContext().setAuthentication(claims);
        } catch (SecurityException se) {
            SecurityContextHolder.clearContext();
            log.warn("JwtAuthenticationFilter.doFilterInternal() : {}", se.getErrorMessage());
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            log.error("JwtAuthenticationFilter.doFilterInternal()", e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private String extractJwtToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.startsWith(header, "Bearer ")) {
            return header.substring(7); // "Bearer " 부분을 잘라낸 JWT 토큰 반환
        }
        return null;
    }

}
