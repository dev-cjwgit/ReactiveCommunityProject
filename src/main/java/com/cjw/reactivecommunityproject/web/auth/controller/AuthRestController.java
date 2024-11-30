package com.cjw.reactivecommunityproject.web.auth.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthJwtTokenVO;
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
    public ResponseEntity<RestResponseVO<Void>> register(@RequestBody @Validated(AuthValidationGroup.register.class) AuthRegisterVO authRegisterVO) {
        return ResponseEntity.ok(authRestService.register(authRegisterVO));
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponseVO<AuthJwtTokenVO>> login(@RequestBody @Validated(AuthValidationGroup.login.class) AuthLoginVO authLoginVO) {
        return ResponseEntity.ok(authRestService.login(authLoginVO));
    }
}
