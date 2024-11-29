package com.cjw.reactivecommunityproject.common.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.util.AntPathMatcher;

import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements AuthorizationManager<RequestAuthorizationContext> {
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final AuthorizationDecision TRUE = new AuthorizationDecision(true);
    private static final AuthorizationDecision FALSE = new AuthorizationDecision(false);


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        return TRUE;
    }
}
