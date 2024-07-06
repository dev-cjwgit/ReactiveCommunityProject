package com.devcjw.reactivecommunity.admin.service.impl

import com.devcjw.reactivecommunity.admin.dao.AdminDAO
import com.devcjw.reactivecommunity.admin.model.domain.*
import com.devcjw.reactivecommunity.admin.model.entity.*
import com.devcjw.reactivecommunity.admin.service.AdminService
import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class AdminServiceImpl(
    private val adminDAO: AdminDAO
) : AdminService {
    private val logger = KotlinLogging.logger {}

    override fun getLevelList(rcUser: RcUserJwtClaims): Flux<RestResponseVO<RepAdminLevelListVO>> {
        /**
         * 1. 유저 등급 목록 가져오기
         * 2. 결과 맵핑 및 반환
         */
        return adminDAO.selectLevelList()
            .map {
                val repAdminLevelListVO = RepAdminLevelListVO(it.uid, it.name, it.createdAt, it.updatedAt)
                RestResponseVO(result = true, data = repAdminLevelListVO)
            }
    }

    override fun insertLevel(
        rcUser: RcUserJwtClaims,
        reqAdminLevelInsertVO: ReqAdminLevelInsertVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 유저 등급 추가
         */
        return Mono.just(reqAdminLevelInsertVO)
            .map { InAdminLevelInsertVO(it.name) }
            .flatMap {
                adminDAO.insertLevel(it)
            }
            .then(Mono.just(RestResponseVO(true)))
    }

    override fun updateLevel(
        rcUser: RcUserJwtClaims,
        reqAdminLevelUpdateVO: ReqAdminLevelUpdateVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 유저 등급 수정
         */
        return Mono.just(reqAdminLevelUpdateVO)
            .map { InAdminLevelUpdateVO(it.uid, it.name) }
            .flatMap {
                adminDAO.updateLevel(it)
            }
            .then(Mono.just(RestResponseVO(true)))
    }

    override fun deleteLevel(
        rcUser: RcUserJwtClaims,
        reqAdminLevelDeleteVO: ReqAdminLevelDeleteVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 유저 등급 삭제
         */
        return Mono.just(reqAdminLevelDeleteVO)
            .flatMap {
                adminDAO.deleteLevel(it.uid)
            }
            .then(Mono.just(RestResponseVO(true)))
    }

    override fun getResourceList(rcUser: RcUserJwtClaims): Flux<RestResponseVO<RepAdminResourceListVO>> {
        /**
         * 1. 자원 패턴 목록 가져오기
         * 2. 결과 맵핑 및 반환
         */
        return adminDAO.selectResourceList()
            .map {
                val repAdminResourceListVO = RepAdminResourceListVO(
                    it.uid, it.method, it.pattern, it.description, it.createdAt, it.updatedAt
                )
                RestResponseVO(result = true, data = repAdminResourceListVO)
            }
    }

    override fun insertResource(
        rcUser: RcUserJwtClaims,
        reqAdminResourceInsertVO: ReqAdminResourceInsertVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 자원 패턴 추가
         */
        return Mono.just(reqAdminResourceInsertVO)
            .map { InAdminResourceInsertVO(it.method, it.pattern, it.description) }
            .flatMap {
                adminDAO.insertResource(it)
            }
            .then(Mono.just(RestResponseVO(true)))
    }

    override fun updateResource(
        rcUser: RcUserJwtClaims,
        reqAdminResourceUpdateVO: ReqAdminResourceUpdateVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 자원 패턴 수정
         */
        return Mono.just(reqAdminResourceUpdateVO)
            .map { InAdminResourceUpdateVO(it.uid, it.method, it.pattern, it.description) }
            .flatMap {
                adminDAO.updateResource(it)
            }
            .then(Mono.just(RestResponseVO(true)))
    }

    override fun deleteResource(
        rcUser: RcUserJwtClaims,
        reqAdminResourceDeleteVO: ReqAdminResourceDeleteVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 자원 패턴 삭제
         */
        return Mono.just(reqAdminResourceDeleteVO)
            .flatMap {
                adminDAO.deleteResource(it.uid)
            }
            .then(Mono.just(RestResponseVO(true)))
    }

    override fun getRoleList(rcUser: RcUserJwtClaims): Flux<RestResponseVO<RepAdminRoleListVO>> {
        /**
         * 1. 등급별 자원 정보 목록 가져오기
         * 2. 결과 맵핑 및 반환
         */
        return adminDAO.selectRoleList()
            .map {
                val repAdminRoleListVO = RepAdminRoleListVO(
                    it.uid,
                    it.levelUid,
                    it.levelName,
                    it.resourceUid,
                    it.resourcePattern,
                    it.resourceDescription,
                    it.createdAt,
                    it.updatedAt
                )
                RestResponseVO(result = true, data = repAdminRoleListVO)
            }
    }

    override fun insertRole(
        rcUser: RcUserJwtClaims,
        reqAdminRoleInsertVO: ReqAdminRoleInsertVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 등급별 자원 등록
         */
        return Mono.just(reqAdminRoleInsertVO)
            .map { InAdminRoleInsertVO(it.levelUid, it.resourceUid) }
            .flatMap {
                adminDAO.insertRole(it)
            }
            .then(Mono.just(RestResponseVO(true)))
    }

    override fun updateRole(
        rcUser: RcUserJwtClaims,
        reqAdminRoleUpdateVO: ReqAdminRoleUpdateVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 등급별 자원 수정
         */
        return Mono.just(reqAdminRoleUpdateVO)
            .map { InAdminRoleUpdateVO(it.uid, it.levelUid, it.resourceUid) }
            .flatMap {
                adminDAO.updateRole(it)
            }
            .then(Mono.just(RestResponseVO(true)))
    }

    override fun deleteRole(
        rcUser: RcUserJwtClaims,
        reqAdminRoleDeleteVO: ReqAdminRoleDeleteVO
    ): Mono<RestResponseVO<Void>> {
        /**
         * 1. 등급별 자원 삭제
         */
        return Mono.just(reqAdminRoleDeleteVO)
            .flatMap {
                adminDAO.deleteRole(it.uid)
            }
            .then(Mono.just(RestResponseVO(true)))
    }
}