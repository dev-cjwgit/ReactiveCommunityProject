package com.devcjw.reactivecommunity.auth.controller

import com.devcjw.reactivecommunity.auth.model.domain.*
import com.devcjw.reactivecommunity.auth.service.AuthRestService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RequestMapping("/auth")
@RestController
@Tag(name = "인증 컨트롤러", description = "사용자의 인증/인가를 담당하는 컨트롤러")
class AuthRestController(
        private val authRestService: AuthRestService,
) {
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "Access/Refresh 토큰을 발급받는 API")
    fun login(@RequestBody authRepTokenVO: ReqAuthLoginVO): Mono<RestResponseVO<RepAuthTokenVO>> {
        return authRestService.login(authRepTokenVO)
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "RcCommunity에 가입을 하는 API")
    fun signup(@RequestBody reqAuthSignupVO: ReqAuthSignupVO): Mono<RestResponseVO<Void>> {
        return authRestService.signup(reqAuthSignupVO)
    }

    @PostMapping("/check")
    @Operation(summary = "권한 확인", description = "특정 API에 권한 여부를 체크하는 API")
    fun check(@RequestBody reqAuthCheckVO: ReqAuthCheckVO): Mono<RestResponseVO<Void>> {
        return authRestService.check(reqAuthCheckVO)
    }

    @PostMapping("/reissue")
    @Operation(summary = "엑세스 토큰 재발급", description = "엑세스 토큰을 재발급 받는 API")
    fun reissue(@RequestBody reqAuthReissueVO: ReqAuthReissueVO): Mono<RestResponseVO<RepAuthReissueVO>> {
        return authRestService.reissue(reqAuthReissueVO)
    }
}