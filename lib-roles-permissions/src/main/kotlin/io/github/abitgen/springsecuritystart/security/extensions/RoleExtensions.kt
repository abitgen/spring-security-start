package io.github.abitgen.springsecuritystart.security.extensions

import io.github.abitgen.springsecuritystart.security.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.io.Serializable

class RoleExtensions {
}

fun UserRole.grantedAuthorities(): Set<GrantedAuthority> {
    return (permissions.map { SimpleGrantedAuthority(it.name) }.toMutableList() + SimpleGrantedAuthority("ROLE_"+name)).toSet()
}