package io.github.abitgen.springsecuritystart.config

import io.github.abitgen.springsecuritystart.security.UserRole
import io.github.abitgen.springsecuritystart.security.extensions.grantedAuthorities
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class ApplicationSecurityConfig constructor(passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {

    private val userDetailsService: UserDetailsService by lazy{


        val adminUser = User.builder().username("admin")
                .password(passwordEncoder.encode("pass123"))
//                .roles(UserRole.ADMIN.name)  moved to authorities
                .authorities(UserRole.ADMIN.grantedAuthorities())
                .build()

        val adminTraineeUser = User.builder().username("admint")
                .password(passwordEncoder.encode("pass123"))
//                .roles(UserRole.ADMINTRAINEE.name) moved to authorities
                .authorities(UserRole.ADMINTRAINEE.grantedAuthorities())
                .build()

        val studentA = User.builder().username("studA")
                .password(passwordEncoder.encode("pass"))
//                .roles(UserRole.STUDENT.name) moved to authorities
                .authorities(UserRole.STUDENT.grantedAuthorities())
                .build()

        InMemoryUserDetailsManager(adminUser, adminTraineeUser, studentA)

    }

    override fun configure(http: HttpSecurity?) {

        http
                ?.csrf()?.disable()  // POST, PUT mappings were not working TODO should be handled differently

                ?.authorizeRequests()
                /* Authenticate all requests */

                ?.antMatchers("/","index","/css/*","/js/*")
                /* Match resources in the mentioned matching paths */

                    ?.permitAll()
                    /* Access is granted for any user, Authentication is not need*/

                ?.antMatchers("/api/management/**")
                /* Ordering of path matters, give access to specific paths first and then generalize*/

                    ?.hasRole(UserRole.ADMIN.name)
                    /* Only admin has access to management apis*/

                ?.antMatchers("/api/**")
                /* Match resources in the mentioned matching paths */

                    ?.hasRole(UserRole.STUDENT.name)
                    /* Only by Student Role */

                ?.anyRequest()
                /* Every other requests*/

                ?.authenticated()
                /* Must be authenticated*/

                ?.and()
                /* Chains to HttpSecurityBuilder*/

                ?.httpBasic()
                /* Adds Basic Authentication*/

    }

    @Bean
    override fun userDetailsService(): UserDetailsService = userDetailsService


}