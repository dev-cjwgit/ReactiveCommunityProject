package com.cjw.reactivecommunityproject.common.spring.component;

import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.exception.model.RcCommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RcUserComponent {

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserRoleUid(){
        var auth = getAuthentication();

        if(auth == null){
            throw new RcCommonException(RcCommonErrorMessage.UNAUTHORIZED_USER);
        }

        return NumberUtils.toLong(auth.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null));
    }
    public String getUserUid() {
        var auth = getAuthentication();

        if (auth == null) {
            throw new RcCommonException(RcCommonErrorMessage.UNAUTHORIZED_USER);
        }

        return auth.getName();
    }
}
