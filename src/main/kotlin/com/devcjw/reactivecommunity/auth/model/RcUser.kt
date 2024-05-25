package com.devcjw.reactivecommunity.auth.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Table("RC_USER_INFO")
data class RcUser(
    @Id val uid: String,
    val email: String,
    val pw: String,
    val name: String,
    val nickname: String,
    val levelUid: Long = 0,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime

) : UserDetails {

    fun getLevel(): Long = levelUid

    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(SimpleGrantedAuthority(levelUid.toString()))

    override fun getPassword(): String = pw

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}