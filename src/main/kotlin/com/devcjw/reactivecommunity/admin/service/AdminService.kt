package com.devcjw.reactivecommunity.admin.service

import com.devcjw.reactivecommunity.admin.model.domain.*
import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AdminService {
    fun getLevelList(rcUser: RcUserJwtClaims): Flux<RestResponseVO<RepAdminLevelListVO>>
    fun insertLevel(rcUser: RcUserJwtClaims, reqAdminLevelInsertVO: ReqAdminLevelInsertVO): Mono<RestResponseVO<Void>>
    fun updateLevel(rcUser: RcUserJwtClaims, reqAdminLevelUpdateVO: ReqAdminLevelUpdateVO): Mono<RestResponseVO<Void>>
    fun deleteLevel(rcUser: RcUserJwtClaims, reqAdminLevelDeleteVO: ReqAdminLevelDeleteVO): Mono<RestResponseVO<Void>>
    fun getResourceList(rcUser: RcUserJwtClaims): Flux<RestResponseVO<RepAdminResourceListVO>>
    fun insertResource(rcUser: RcUserJwtClaims, reqAdminResourceInsertVO: ReqAdminResourceInsertVO): Mono<RestResponseVO<Void>>
    fun updateResource(rcUser: RcUserJwtClaims, reqAdminResourceUpdateVO: ReqAdminResourceUpdateVO): Mono<RestResponseVO<Void>>
    fun deleteResource(rcUser: RcUserJwtClaims, reqAdminResourceDeleteVO: ReqAdminResourceDeleteVO): Mono<RestResponseVO<Void>>
    fun getRoleList(rcUser: RcUserJwtClaims): Flux<RestResponseVO<RepAdminRoleListVO>>
    fun insertRole(rcUser: RcUserJwtClaims, reqAdminRoleInsertVO: ReqAdminRoleInsertVO): Mono<RestResponseVO<Void>>
    fun updateRole(rcUser: RcUserJwtClaims, reqAdminRoleUpdateVO: ReqAdminRoleUpdateVO): Mono<RestResponseVO<Void>>
    fun deleteRole(rcUser: RcUserJwtClaims, reqAdminRoleDeleteVO: ReqAdminRoleDeleteVO): Mono<RestResponseVO<Void>>
}