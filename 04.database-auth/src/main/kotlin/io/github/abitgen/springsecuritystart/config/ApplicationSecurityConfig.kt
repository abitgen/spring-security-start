package io.github.abitgen.springsecuritystart.config

import io.github.abitgen.springsecuritystart.security.UserPermission.COURSE_WRITE
import io.github.abitgen.springsecuritystart.security.UserPermission.STUDENT_WRITE
import io.github.abitgen.springsecuritystart.security.UserRole.*
import io.github.abitgen.springsecuritystart.security.extensions.grantedAuthorities
import io.github.abitgen.springsecuritystart.service.AppUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.config.annotation.web.builders.WebSecurity



@Configuration
@EnableWebSecurity
class ApplicationSecurityConfig constructor(val appUserDetailsService: AppUserDetailsService, val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {

    /**
     * Added so that h2 database console is accessible
     */
    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web
                .ignoring()
                .antMatchers("/h2-console/**")
    }

    override fun configure(http: HttpSecurity?) {

        val managementApi = arrayOf("/api/management/**")



        http
                ?.csrf()?.disable()  // When to use CSRF https://docs.spring.io/spring-security/site/docs/3.2.0.CI-SNAPSHOT/reference/html/csrf.html
                /*Apis dont require CSRF, as its related to browser cookies*/

                ?.authorizeRequests()
                /* Authenticate all requests */

                ?.antMatchers("/","index","/css/*","/js/*","/h2-console/**")
                /* Match resources in the mentioned matching paths */

                    ?.permitAll()
                    /* Access is granted for any user, Authentication is not need*/


                ?.antMatchers( HttpMethod.POST, *managementApi)?.hasAuthority(STUDENT_WRITE.name)
                ?.antMatchers( HttpMethod.DELETE, *managementApi)?.hasAuthority(STUDENT_WRITE.name)
                ?.antMatchers( HttpMethod.PUT, *managementApi)?.hasAuthority(STUDENT_WRITE.name)

                ?.antMatchers( HttpMethod.POST, *managementApi)?.hasAuthority(COURSE_WRITE.name)
                ?.antMatchers( HttpMethod.PUT, *managementApi)?.hasAuthority(COURSE_WRITE.name)
                ?.antMatchers( HttpMethod.DELETE, *managementApi)?.hasAuthority(COURSE_WRITE.name)
                ?.antMatchers( HttpMethod.GET, *managementApi)?.hasAnyRole(ADMIN.name, ADMINTRAINEE.name)
                /* Ordering of path matters, give access to specific paths first and then generalize*/



                ?.antMatchers("/api/**")
                /* Match resources in the mentioned matching paths */

                    ?.hasAnyRole(STUDENT.name)
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

   /* @Bean
    override fun userDetailsService(): UserDetailsService = userDetailsService*/

    @Bean
    fun daoAuthenticationProvider() : DaoAuthenticationProvider{
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder)
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService)
        return daoAuthenticationProvider;
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(daoAuthenticationProvider())
    }


}