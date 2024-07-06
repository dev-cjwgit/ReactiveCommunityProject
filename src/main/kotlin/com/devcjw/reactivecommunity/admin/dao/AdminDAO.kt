package com.devcjw.reactivecommunity.admin.dao

import com.devcjw.reactivecommunity.admin.model.entity.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AdminDAO {
    fun selectLevelList(): Flux<OutAdminLevelSelectVO>
    fun insertLevel(inAdminLevelInsertVO: InAdminLevelInsertVO): Mono<Void>
    fun updateLevel(inAdminLevelUpdateVO: InAdminLevelUpdateVO): Mono<Void>
    fun deleteLevel(uid: Long): Mono<Void>

    fun selectResourceList(): Flux<OutAdminResourceSelectVO>
    fun insertResource(inAdminResourceInsertVO: InAdminResourceInsertVO): Mono<Void>
    fun updateResource(inAdminResourceUpdateVO: InAdminResourceUpdateVO): Mono<Void>
    fun deleteResource(uid: Long): Mono<Void>

    fun selectRoleList(): Flux<OutAdminRoleSelectVO>
    fun insertRoleResource(inAdminRoleInsertVO: InAdminRoleInsertVO): Mono<Void>
    fun updateRoleResource(inAdminRoleUpdateVO: InAdminRoleUpdateVO): Mono<Void>
    fun deleteRoleResource(inAdminRoleDeleteVO: InAdminRoleDeleteVO): Mono<Void>
}