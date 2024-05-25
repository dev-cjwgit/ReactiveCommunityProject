package com.devcjw.reactivecommunity.auth.service.impl

import com.devcjw.reactivecommunity.auth.model.domain.AuthRepTokenVO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqLoginDTO
import com.devcjw.reactivecommunity.auth.repository.AuthRepository
import com.devcjw.reactivecommunity.auth.service.AuthService
import com.devcjw.reactivecommunity.auth.service.JwtService
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Slf4j
@Service
class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val jwtService: JwtService
) : AuthService {
    override fun login(loginDTO: AuthReqLoginDTO): Mono<AuthRepTokenVO> {
        /**
         * 1. 계정 정보 조회
         * 2. 비밀번호 매칭
         * 3. 승인대기 유무 확인
         * 4. JWT 토큰 생성 및 반환
         */

        // 1
        return authRepository.findByEmail(loginDTO.email)
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_USER_EMAIL_EXCEPTION)))
            .filter {
                // 2
                loginDTO.password == it.password
            }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_USER_PASSWORD_EXCEPTION)))
            .filter {
                // 3
                it.authorities.stream().findFirst().get().authority != "0"
            }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.LISTEN_JOIN_USER_EXCEPTION)))
            .flatMap {
                Mono.defer {
                    val accessToken = jwtService.createAccessToken(it.uid, it.getLevel())
                    val refreshToken = jwtService.createRefreshToken(it.uid, it.getLevel())

                    Mono.just(AuthRepTokenVO(accessToken, refreshToken))
                }
            }
    }
}