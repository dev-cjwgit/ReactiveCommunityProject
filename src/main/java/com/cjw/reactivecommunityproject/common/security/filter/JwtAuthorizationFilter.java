package com.cjw.reactivecommunityproject.common.security.filter;

import com.cjw.reactivecommunityproject.common.security.dao.SecurityDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.util.AntPathMatcher;

import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements AuthorizationManager<RequestAuthorizationContext> {
    private final SecurityDAO securityDAO;


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
        var resourceByRoleUid = securityDAO.selectResourceByRoleUid(NumberUtils.toInt(roleUid));

        return resourceByRoleUid.parallelStream()
                .filter(o -> StringUtils.equalsAnyIgnoreCase(o.method().name(), "ALL") || StringUtils.equalsAnyIgnoreCase(o.method().name(), method))
                .anyMatch(o -> antPathMatcher.match(o.urlPattern(), path))
                ? TRUE : FALSE;
    }
}
