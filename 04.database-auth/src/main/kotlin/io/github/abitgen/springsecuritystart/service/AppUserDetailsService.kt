package io.github.abitgen.springsecuritystart.service

import io.github.abitgen.springsecuritystart.dto.AppUserDetails
import io.github.abitgen.springsecuritystart.repo.AppUserFindRepo
import io.github.abitgen.springsecuritystart.security.UserPermission
import io.github.abitgen.springsecuritystart.security.UserRole
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service()
class AppUserDetailsService(@Qualifier("mockAppUserRepo") val repo: AppUserFindRepo, val passwordEncoder: PasswordEncoder) : UserDetailsService{
    override fun loadUserByUsername(username: String?): UserDetails {

        val user = repo.findByName(username).orElseThrow { UsernameNotFoundException(" Username $username not found") }
        return AppUserDetails(
                accountNonExpired = true,
                accountNonLocked = true,
                credentialNonExpired = true,
                enabled = true,
                username = user.name,
                password = user.password,
                authorities = mutableSetOf( SimpleGrantedAuthority("ROLE_"+UserRole.ADMIN.name))
        );
    }
}