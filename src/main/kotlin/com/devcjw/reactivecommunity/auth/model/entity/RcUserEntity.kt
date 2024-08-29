package com.devcjw.reactivecommunity.auth.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Table("rc_user")
data class RcUserEntity(
        @Id val uid: String,
        val roleUid: Long,
        val email: String,
        val pw: String,
        val state: String,
        val acceptUserUid: String? = null,
        val name: String,
        val nickname: String,
        val joinedRegion: String,
        val createdUtcAt: LocalDateTime,
        val updatedUtcAt: LocalDateTime

) : UserDetails {

    fun getRole(): Long = roleUid

    override fun getAuthorities(): Collection<GrantedAuthority> =
            listOf(SimpleGrantedAuthority(roleUid.toString()))

    override fun getPassword(): String = pw

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}