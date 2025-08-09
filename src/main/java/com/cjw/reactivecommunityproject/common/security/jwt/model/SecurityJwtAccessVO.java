package com.cjw.reactivecommunityproject.common.security.jwt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.Builder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Builder
public record SecurityJwtAccessVO(
        String userUid
        , Integer roleUid
) implements Authentication, Serializable {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(String.valueOf(roleUid)));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException("SecurityAccessJwt is immutable and authentication status cannot be changed.");
    }

    @Override
    public String getName() {
        return userUid;
    }
}
