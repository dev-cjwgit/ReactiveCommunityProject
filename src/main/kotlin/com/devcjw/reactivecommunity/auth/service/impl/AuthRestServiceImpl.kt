package com.devcjw.reactivecommunity.auth.service.impl

import com.devcjw.reactivecommunity.auth.dao.AuthDAO
import com.devcjw.reactivecommunity.auth.manager.AuthManager
import com.devcjw.reactivecommunity.auth.model.domain.*
import com.devcjw.reactivecommunity.common.model.entity.RcUserEntity
import com.devcjw.reactivecommunity.common.repository.RcUserRepository
import com.devcjw.reactivecommunity.auth.service.AuthRestService
import com.devcjw.reactivecommunity.auth.service.JwtService
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.ZonedDateTime
import java.util.*

@Service
class AuthRestServiceImpl(
    private val authDAO: AuthDAO,
    private val rcUserRepository: RcUserRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val redisTemplate: ReactiveRedisTemplate<String, Any>,
    private val authManager: AuthManager,
) : AuthRestService {
    private val logger = KotlinLogging.logger {}

    override fun login(loginDTO: ReqAuthLoginVO): Mono<RestResponseVO<RepAuthTokenVO>> {
        logger.info { "login : $loginDTO" }
        /**
         * 1. 계정 정보 조회
         * 2. 비밀번호 매칭
         * 3. 승인대기 유무 확인
         * 4. 중복 로그인 확인
         * 5. JWT 토큰 생성
         * 6. Redis 토큰 저장 및 반환
         */

        // 1
        return rcUserRepository.findByEmail(loginDTO.email)
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_USER_EMAIL_EXCEPTION)))
                // 2
                .filter { userEntity ->
                    val result = passwordEncoder.matches(loginDTO.password, userEntity.password)
                    logger.info { "match password : $result" }
                    result
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_USER_PASSWORD_EXCEPTION)))
                // 3
                .filter {
                    val result = it.state == "ACCEPT"
                    logger.info { "join listener : $result" }
                    result
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.LISTEN_JOIN_USER_EXCEPTION)))
                // 4
                .flatMap { userEntity ->
                    redisTemplate.opsForValue().get(userEntity.uid)
                            .flatMap { existingRefreshToken ->
                                if (existingRefreshToken != null) {
                                    logger.info { "already login" }
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
                        val accessToken = jwtService.createAccessToken(it.uid, it.getRole())
                        val refreshToken = jwtService.createRefreshToken(it.uid, it.getRole())
                        logger.info { "login success : accessToken => $accessToken refreshToken => $refreshToken" }
                        // 6
                        redisTemplate.opsForValue()
                                .set(it.uid, refreshToken)
                                .thenReturn(
                                        RestResponseVO(
                                                result = true,
                                                data = RepAuthTokenVO(
                                                        accessToken, refreshToken
                                                )
                                        )
                                )
                    }
                }
    }

    override fun signup(reqAuthSignupVO: ReqAuthSignupVO): Mono<RestResponseVO<Void>> {
        logger.info { "signup $reqAuthSignupVO" }

        /**
         * 1. 이메일 중복 체크
         * 2. 닉네임 중복 체크
         * 3. 데이터 추가
         */
        return rcUserRepository.findByEmail(reqAuthSignupVO.email)
                // 1
                .flatMap {
                    logger.info { "exists email ${it.email}" }
                    Mono.error<Boolean>(RcException(RcErrorMessage.ALREADY_JOIN_EMAIL_EXCEPTION))
                }
                .switchIfEmpty(
                        // 2
                        rcUserRepository.findByNickname(reqAuthSignupVO.nickname)
                                .flatMap {
                                    Mono.error<Boolean>(RcException(RcErrorMessage.ALREADY_USE_NICKNAME_EXCEPTION))
                                }
                                // 3
                                .switchIfEmpty(
                                        authDAO.insertRcUser(
                                                RcUserEntity(
                                                        uid = UUID.randomUUID().toString(),
                                                        email = reqAuthSignupVO.email,
                                                        roleUid = 0,
                                                        state = "LISTEN",
                                                        pw = passwordEncoder.encode(reqAuthSignupVO.password),
                                                        name = reqAuthSignupVO.name,
                                                        nickname = reqAuthSignupVO.nickname,
                                                        createdUtcAt = ZonedDateTime.now(),
                                                        updatedUtcAt = ZonedDateTime.now(),
                                                        joinedRegion = "KOR",

                                                        )
                                        ).thenReturn(true)
                                )
                )
                .then(
                        Mono.just(RestResponseVO(true, null))
                )
    }

    override fun check(
            reqAuthCheckVO: ReqAuthCheckVO,
    ): Mono<RestResponseVO<Void>> {
        logger.info { "auth check : $reqAuthCheckVO" }
        /**
         * 1. Valid Check (인증 체크}
         * 2. RcUser 인증 객체 생성
         * 3. 인가 체크
         * 4. 결과 반환
         */
        return Mono.just(jwtService.validateToken(reqAuthCheckVO.accessToken))
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.AUTHENTICATION_EXCEPTION)))
                .map {
                    val result = jwtService.getRcUser(reqAuthCheckVO.accessToken)
                    logger.info { "rc info : $result" }
                    result
                }
                .flatMap {
                    val authentication = UsernamePasswordAuthenticationToken(
                            it,
                            null,
                            listOf(SimpleGrantedAuthority(it.level.toString()))
                    )

                    authManager.check(Mono.just(authentication), "GET", reqAuthCheckVO.path)
                }
                .flatMap<RestResponseVO<Void>?> { decision ->
                    Mono.just(RestResponseVO(decision))
                }
                .onErrorReturn(RestResponseVO(false))
    }

    override fun reissue(reqAuthReissueVO: ReqAuthReissueVO): Mono<RestResponseVO<RepAuthReissueVO>> {
        logger.info { "reissue : $reqAuthReissueVO" }
        /**
         * 1. r2dbc 에서 계정 정보 조회
         * 2. redis 에서 uid 조회
         * 3. refresh 확인
         */
        // 1
        return rcUserRepository.findByEmail(reqAuthReissueVO.email)
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_USER_EMAIL_EXCEPTION)))
                .flatMap { userEntity ->
                    // 2
                    redisTemplate.opsForValue().get(userEntity.uid)
                            .flatMap { storedRefreshToken ->
                                // 3
                                if (storedRefreshToken == reqAuthReissueVO.refreshToken) {
                                    // Refresh token이 일치하면 새로운 Access token을 생성
                                    val newAccessToken = jwtService.createAccessToken(userEntity.uid, userEntity.getRole())
                                    Mono.just(RestResponseVO(result = true, data = RepAuthReissueVO(newAccessToken)))
                                } else {
                                    // Refresh token이 일치하지 않으면 오류 발생
                                    Mono.error(RcException(RcErrorMessage.NOT_MATCH_REFRESH_TOKEN_EXCEPTION))
                                }
                            }
                            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_LOGIN_INFO_EXCEPTION)))
                }
    }
}