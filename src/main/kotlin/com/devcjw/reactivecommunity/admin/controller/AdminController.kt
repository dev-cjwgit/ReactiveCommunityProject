package com.devcjw.reactivecommunity.admin.controller

import com.devcjw.reactivecommunity.admin.model.domain.*
import com.devcjw.reactivecommunity.admin.service.AdminService
import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "관리자 컨트롤러", description = "Rc Community Role을 담당하는 컨트롤러")
class AdminController(
    private val adminService: AdminService
) {
    /**
     * 1. 유저 레벨 CRUD
     * 2. 유저 자원 접근 CRUD
     * 3. 유저 자원 맵핑 CRUD
     */

    // region 1. 유저 등급 관련
    @GetMapping("/level")
    @Operation(summary = "유저 등급 목록", description = "유저 등급을 불러오는 API")
    fun getLevelList(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
    ): Flux<RestResponseVO<RepAdminLevelListVO>> {
        TODO("NOT YET IMPLEMENTATION")
    }

    @PostMapping("/level")
    @Operation(summary = "유저 등급 추가", description = "유저 등급을 새로 추가하는 API")
    fun insertLevel(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqAdminLevelInsertVO: ReqAdminLevelInsertVO
    ): Mono<RestResponseVO<Void>> {
        TODO("NOT YET IMPLEMENTATION")
    }

    @PatchMapping("/level")
    @Operation(summary = "유저 등급 수정", description = "유저 등급을 수정하는 API")
    fun updateLevel(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqAdminLevelUpdateVO: ReqAdminLevelUpdateVO
    ): Mono<RestResponseVO<Void>> {
        TODO("NOT YET IMPLEMENTATION")
    }

    @DeleteMapping("/level")
    @Operation(summary = "유저 등급 삭제", description = "유저 등급을 삭제하는 API")
    fun deleteLevel(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqAdminLevelDeleteVO: ReqAdminLevelDeleteVO
    ): Mono<RestResponseVO<Void>> {
        TODO("NOT YET IMPLEMENTATION")
    }
    // endregion

    // region 2. 자원 접근 패턴 관련
    @GetMapping("/resource")
    @Operation(summary = "자원 패턴 목록", description = "자원 패턴 리스트를 불러오는 API")
    fun getResourceList(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
    ): Flux<RestResponseVO<RepAdminResourceListVO>> {
        TODO("NOT YET IMPLEMENTATION")
    }

    @PostMapping("/resource")
    @Operation(summary = "리소스 등록", description = "리소스 패턴을 등록하는 API")
    fun insertResource(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody repAdminResourceInsertVO: RepAdminResourceInsertVO
    ): Mono<RestResponseVO<Void>> {
        TODO("NOT YET IMPLEMENTATION")
    }

    @PatchMapping("/resource")
    @Operation(summary = "리소스 수정", description = "리소스 패턴을 수정하는 API")
    fun updateResource(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody repAdminResourceUpdateVO: RepAdminResourceUpdateVO
    ): Mono<RestResponseVO<Void>> {
        TODO("NOT YET IMPLEMENTATION")
    }

    @DeleteMapping("/resource")
    @Operation(summary = "리소스 삭제", description = "리소스 패턴을 삭제하는 API")
    fun deleteResource(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody repAdminResourceDeleteVO: RepAdminResourceDeleteVO
    ): Mono<RestResponseVO<Void>> {
        TODO("NOT YET IMPLEMENTATION")
    }
    // endregion

    // region 3. 유저 등급 별 자원 접근 권한 관련
    @GetMapping("/role")
    @Operation(summary = "등급별 자원 정보 목록", description = "등급 별 자원 정보를 불러오는 API")
    fun getRoleList(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
    ): Flux<RestResponseVO<RepAdminRoleListVO>> {
        TODO("NOT YET IMPLEMENTATION")
    }

    @PostMapping("/role")
    @Operation(summary = "등급별 자원 등록", description = "등급별 자원을 등록하는 API")
    fun insertRole(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody repAdminRoleInsertVO: RepAdminRoleInsertVO
    ) {
        TODO("NOT YET IMPLEMENTATION")
    }

    @PatchMapping("/role")
    @Operation(summary = "등급별 자원 수정", description = "등급별 자원을 수정하는 API")
    fun updateRole(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqAdminRoleUpdateVO: ReqAdminRoleUpdateVO
    ) {
        TODO("NOT YET IMPLEMENTATION")
    }

    @DeleteMapping("/role")
    @Operation(summary = "등급별 자원 삭제", description = "등급별 자원을 삭제하는 API")
    fun deleteRole(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqAdminRoleDeleteVO: ReqAdminRoleDeleteVO
    ) {
        TODO("NOT YET IMPLEMENTATION")
    }
    // endregion
}