package io.github.abitgen.springsecuritystart.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class ApplicationSecurityConfig : WebSecurityConfigurerAdapter() {


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
}