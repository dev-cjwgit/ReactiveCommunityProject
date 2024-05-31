package com.devcjw.reactivecommunity.auth.service.impl

import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.manager.AuthManager
import com.devcjw.reactivecommunity.auth.model.domain.*
import com.devcjw.reactivecommunity.auth.model.entity.RcUser
import com.devcjw.reactivecommunity.auth.repository.AuthRepository
import com.devcjw.reactivecommunity.auth.service.AuthService
import com.devcjw.reactivecommunity.auth.service.JwtService
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*

@Service
class AuthServiceImpl(
    private val authDAO: AuthDAO,
    private val authRepository: AuthRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val redisTemplate: ReactiveRedisTemplate<String, Any>,
    private val authManager: AuthManager,
) : AuthService {
    override fun login(loginDTO: AuthReqLoginDTO): Mono<RestResponseVO<AuthRepTokenVO>> {
        /**
         * 1. 계정 정보 조회
         * 2. 비밀번호 매칭
         * 3. 승인대기 유무 확인
         * 4. 중복 로그인 확인
         * 5. JWT 토큰 생성
         * 6. Redis 토큰 저장 및 반환
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
            .flatMap { userEntity ->
                redisTemplate.opsForValue().get(userEntity.uid)
                    .flatMap { existingRefreshToken ->
                        if (existingRefreshToken != null) {
                            Mono.error(RcException(RcErrorMessage.DUPLICATE_LOGIN_EXCEPTION))
                        } else {
                            Mono.just(userEntity)
                        }
                    }
                    .switchIfEmpty(Mono.just(userEntity))
            }
            // 5
            .flatMap {
                Mono.defer {
                    val accessToken = jwtService.createAccessToken(it.uid, it.getLevel())
                    val refreshToken = jwtService.createRefreshToken(it.uid, it.getLevel())

                    // 6
                    redisTemplate.opsForValue()
                        .set(it.uid, refreshToken)
                        .thenReturn(
                            RestResponseVO(
                                result = true,
                                data = AuthRepTokenVO(
                                    accessToken, refreshToken
                                )
                            )
                        )
                }
            }
    }

    override fun signup(authReqSignupDTO: AuthReqSignupDTO): Mono<RestResponseVO<Void>> {
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
                                createdAt = LocalDateTime.now(),
                                updatedAt = LocalDateTime.now(),
                            )
                        ).thenReturn(true)
                    )
            )
            .then(
                Mono.just(RestResponseVO(true, null))
            )
    }

    override fun check(
        authReqCheckDTO: AuthReqCheckDTO,
        serverWebExchange: ServerWebExchange
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. Valid Check
         */
        return Mono.just(jwtService.validateToken(authReqCheckDTO.accessToken))
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.AUTHENTICATION_EXCEPTION)))
            .map {
                jwtService.getRcUser(authReqCheckDTO.accessToken)
            }
            .flatMap {
                val authentication = UsernamePasswordAuthenticationToken(
                    it,
                    null,
                    listOf(SimpleGrantedAuthority(it.level.toString()))
                )

                authManager.check(Mono.just(authentication), "GET", authReqCheckDTO.path)
            }
            .flatMap { decision ->
                Mono.just(RestResponseVO(decision))
            }
    }

    override fun reissue(authReqReissueDTO: AuthReqReissueDTO): Mono<RestResponseVO<AuthRepReissueVO>> {
        /**
         * 1. r2dbc 에서 계정 정보 조회
         * 2. redis 에서 uid 조회
         * 3. refresh 확인
         */
        // 1
        return authRepository.findByEmail(authReqReissueDTO.email)
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_USER_EMAIL_EXCEPTION)))
            .flatMap { userEntity ->
                // 2
                redisTemplate.opsForValue().get(userEntity.uid)
                    .flatMap { storedRefreshToken ->
                        // 3
                        if (storedRefreshToken == authReqReissueDTO.refreshToken) {
                            // Refresh token이 일치하면 새로운 Access token을 생성
                            val newAccessToken = jwtService.createAccessToken(userEntity.uid, userEntity.getLevel())
                            Mono.just(RestResponseVO(result = true, data = AuthRepReissueVO(newAccessToken)))
                        } else {
                            // Refresh token이 일치하지 않으면 오류 발생
                            Mono.error(RcException(RcErrorMessage.NOT_MATCH_REFRESH_TOKEN_EXCEPTION))
                        }
                    }
                    .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_LOGIN_INFO_EXCEPTION)))
            }
    }
}