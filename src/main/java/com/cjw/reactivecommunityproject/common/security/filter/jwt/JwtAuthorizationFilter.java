package com.cjw.reactivecommunityproject.common.security.filter.jwt;

import com.cjw.reactivecommunityproject.server.cache.info.custom.service.CacheInfoCustomService;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Strings;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.util.AntPathMatcher;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements AuthorizationManager<RequestAuthorizationContext> {
    private final CacheInfoCustomService cacheInfoCustomService;


    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final AuthorizationDecision TRUE = new AuthorizationDecision(true);
    private static final AuthorizationDecision FALSE = new AuthorizationDecision(false);


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        var path = object.getRequest().getServletPath();
        var method = object.getRequest().getMethod();

        var auth = authentication.get();
        if (auth == null) {
            return FALSE;
        }

        var roleUid = auth.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
        if (roleUid == null) {
            return FALSE;
        }
        if (!NumberUtils.isDigits(roleUid)) {
            return FALSE;
        }

        return cacheInfoCustomService.getManageRoleResourceList(NumberUtils.toInt(roleUid))
                .parallelStream()
                .filter(o -> Strings.CI.equalsAny(o.getMethod().name(), "ALL") || Strings.CI.equalsAny(o.getMethod().name(), method))
                .anyMatch(o -> antPathMatcher.match(o.getUrlPattern(), path))
                ? TRUE : FALSE;
    }
}
