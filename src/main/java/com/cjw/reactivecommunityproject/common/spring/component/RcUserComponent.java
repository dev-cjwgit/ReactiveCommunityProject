package com.cjw.reactivecommunityproject.common.spring.component;

import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.exception.model.RcCommonException;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.service.CacheInfoCustomService;
import io.micrometer.common.util.StringUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RcUserComponent {
    private final CacheInfoCustomService cacheInfoCustomService;

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isFunctionInUser(@NonNull String functionId) {
        var roleUid = this.getUserRoleUid();
        return CollectionUtils.emptyIfNull(cacheInfoCustomService.getManageRoleFunctionList(roleUid))
                .parallelStream()
                .map(CacheInfoCustomRoleFunctionVO::getFunctionName)
                .anyMatch(functionName -> Strings.CS.equals(functionName, functionId));
    }

    @NonNull
    public Integer getUserRoleUid() {
        var auth = this.getAuthentication();

        if (auth == null) {
            throw new RcCommonException(RcCommonErrorMessage.UNAUTHORIZED_USER);
        }
        String role = auth.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
        if (StringUtils.isBlank(role)) {
            throw new RcCommonException(RcCommonErrorMessage.INVALID_ROLE_UID);
        }
        return Integer.parseInt(role);
    }

    @NonNull
    public String getUserUid() {
        var auth = getAuthentication();

        if (auth == null) {
            throw new RcCommonException(RcCommonErrorMessage.UNAUTHORIZED_USER);
        }

        return auth.getName();
    }
}
