package com.devcjw.reactivecommunity.auth.service.impl

import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.model.RcUser
import com.devcjw.reactivecommunity.auth.model.domain.AuthRepTokenVO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqCheckDTO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqLoginDTO
import com.devcjw.reactivecommunity.auth.model.domain.AuthReqSignupDTO
import com.devcjw.reactivecommunity.auth.repository.AuthRepository
import com.devcjw.reactivecommunity.auth.service.AuthService
import com.devcjw.reactivecommunity.auth.service.JwtService
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import lombok.extern.slf4j.Slf4j
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*

@Slf4j
@Service
class AuthServiceImpl(
    private val authDAO: AuthDAO,
    private val authRepository: AuthRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
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
            // 2
            .filter { userEntity ->
                passwordEncoder.matches(loginDTO.password, userEntity.password)
            }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_USER_PASSWORD_EXCEPTION)))
            // 3
            .filter {
                it.authorities.stream().findFirst().get().authority != "0"
            }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.LISTEN_JOIN_USER_EXCEPTION)))

            // 4
            .flatMap {
                Mono.defer {
                    val accessToken = jwtService.createAccessToken(it.uid, it.getLevel())
                    val refreshToken = jwtService.createRefreshToken(it.uid, it.getLevel())

                    Mono.just(AuthRepTokenVO(accessToken, refreshToken))
                }
            }
    }

    override fun signup(authReqSignupDTO: AuthReqSignupDTO): Mono<Boolean> {
        /**
         * 1. 이메일 중복 체크
         * 2. 닉네임 중복 체크
         * 3. 데이터 추가
         */
        return authRepository.findByEmail(authReqSignupDTO.email)
            // 1
            .flatMap {
                Mono.error<Boolean>(RcException(RcErrorMessage.ALREADY_JOIN_EMAIL_EXCEPTION))
            }
            .switchIfEmpty(
                // 2
                authRepository.findByNickname(authReqSignupDTO.nickname)
                    .flatMap {
                        Mono.error<Boolean>(RcException(RcErrorMessage.ALREADY_USE_NICKNAME_EXCEPTION))
                    }
                    // 3
                    .switchIfEmpty(
                        authDAO.insertRcUser(
                            RcUser(
                                uid = UUID.randomUUID().toString(),
                                email = authReqSignupDTO.email,
                                pw = passwordEncoder.encode(authReqSignupDTO.password),
                                name = authReqSignupDTO.name,
                                nickname = authReqSignupDTO.nickname,
                                createAt = LocalDateTime.now(),
                                updateAt = LocalDateTime.now(),
                            )
                        ).then(Mono.just(true))
                    )
            )
    }

    override fun check(authReqCheckDTO: AuthReqCheckDTO): Mono<Boolean> {
        /**
         * 1. Valid Check
         */
        return Mono.just(jwtService.validateToken(authReqCheckDTO.accessToken))
    }
}