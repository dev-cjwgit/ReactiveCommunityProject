package com.cjw.reactivecommunityproject.common.security.filter.api_logging;

import com.cjw.reactivecommunityproject.common.spring.component.RcRedisIdGeneratorComponent;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.api.model.ElasticsearchLogApiDocument;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiLoggingFilter extends OncePerRequestFilter {
    private final ApplicationEventPublisher publisher;
    private final RcRedisIdGeneratorComponent rcRedisIdGeneratorComponent;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        var incrementId = rcRedisIdGeneratorComponent.getNextLogApiId();
        var path = request.getRequestURI();
        var ip = request.getRemoteAddr();

        ZonedDateTime start = ZonedDateTime.now();

        filterChain.doFilter(request, response);

        ZonedDateTime end = ZonedDateTime.now();

        var document = ElasticsearchLogApiDocument.builder()
                .uid(incrementId)
                .url(path)
                .ip(ip)
                .requestTimestamp(start)
                .responseTimestamp(end)
                .build();

        publisher.publishEvent(document);
    }
}
