package com.cjw.reactivecommunityproject.common.spring.rc.filter;

import com.cjw.reactivecommunityproject.common.spring.component.RcRedisIdGeneratorComponent;
import com.cjw.reactivecommunityproject.common.spring.util.CommonUtils;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.api.model.ElasticsearchLogApiDocument;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
public class RcApiLoggingFilter extends OncePerRequestFilter {
    private final ApplicationEventPublisher publisher;
    private final RcRedisIdGeneratorComponent rcRedisIdGeneratorComponent;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        var incrementId = rcRedisIdGeneratorComponent.getNextLogApiId();
        var path = request.getRequestURI();
        var ip = CommonUtils.extractClientIp(request);
        String exceptionMessage = "";
        int httpStatus = 500;

        ZonedDateTime start = ZonedDateTime.now();
        try {
            filterChain.doFilter(request, response);
            httpStatus = response.getStatus();
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
            log.error("RcApiLoggingFilter.Exception", e);
        }
        ZonedDateTime end = ZonedDateTime.now();

        publisher.publishEvent(ElasticsearchLogApiDocument.builder()
                .uid(incrementId)
                .url(path)
                .ip(ip)
                .httpStatus(httpStatus)
                .exceptionMessage(exceptionMessage)
                .requestTimestamp(start)
                .responseTimestamp(end)
                .build()
        );
    }
}
