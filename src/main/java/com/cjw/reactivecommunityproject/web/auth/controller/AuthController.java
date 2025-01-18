package com.cjw.reactivecommunityproject.web.auth.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthReissueJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtAccessTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.service.AuthService;
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
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RestResponseVO<Void>> register(@RequestBody @Validated(AuthValidationGroup.Register.class) AuthRegisterVO authRegisterVO) {
        return ResponseEntity.ok(authService.register(authRegisterVO));
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponseVO<AuthRestJwtTokenVO>> login(@RequestBody @Validated(AuthValidationGroup.Login.class) AuthLoginVO authLoginVO) {
        return ResponseEntity.ok(authService.login(authLoginVO));
    }

    @PostMapping("/reissue")
    public ResponseEntity<RestResponseVO<AuthRestJwtAccessTokenVO>> reissueByRefreshToken(@RequestBody @Validated(AuthValidationGroup.ReissueRefreshToken.class) AuthReissueJwtTokenVO authReissueJwtTokenVO) {
        return ResponseEntity.ok(authService.reissueByRefreshToken(authReissueJwtTokenVO));
    }
}
