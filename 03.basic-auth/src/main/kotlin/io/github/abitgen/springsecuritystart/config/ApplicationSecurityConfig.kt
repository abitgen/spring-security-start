package io.github.abitgen.springsecuritystart.config

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

        InMemoryUserDetailsManager(User.builder().username("abitgen")
                .password(passwordEncoder.encode("pass"))
                .roles("STUDENT")
                .build())

    }

    override fun configure(http: HttpSecurity?) {

        http
                ?.authorizeRequests()
                /* Authenticate all requests */

                ?.antMatchers("/","index","/css/*","/js/*")
                /* Match resources in the mentioned matching paths */

                ?.permitAll()
                /* Access is granted for any user, Authentication is not need*/

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