package io.github.abitgen.springsecuritystart.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AppUserDetails(private val authorities: MutableCollection<out GrantedAuthority>,
                     private val enabled: Boolean,
                     private val username:String,
                     private val credentialNonExpired:Boolean,
                     private val password:String,
                     private val accountNonExpired:Boolean,
                     private val accountNonLocked:Boolean) : UserDetails {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities;
    }

    override fun isEnabled(): Boolean  = enabled

    override fun getUsername(): String  = username

    override fun isCredentialsNonExpired(): Boolean = credentialNonExpired

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = accountNonExpired

    override fun isAccountNonLocked(): Boolean = accountNonLocked
}

