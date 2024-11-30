package com.cjw.reactivecommunityproject.web.auth.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.service.AuthRestService;
import com.cjw.reactivecommunityproject.web.auth.validation.AuthValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/auth")
public class AuthRestController {
    private final AuthRestService authRestService;

    @PostMapping("/register")
    public ResponseEntity<RestResponseVO<Void>> register(@RequestBody @Validated(AuthValidationGroup.register.class) AuthRestRegisterVO authRestRegisterVO) {
        return ResponseEntity.ok(authRestService.register(authRestRegisterVO));
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponseVO<AuthRestJwtTokenVO>> login(@RequestBody @Validated(AuthValidationGroup.login.class) AuthRestLoginVO authRestLoginVO) {
        return ResponseEntity.ok(authRestService.login(authRestLoginVO));
    }
}
