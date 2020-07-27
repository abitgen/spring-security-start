package io.github.abitgen.springsecuritystart.dev

import io.github.abitgen.springsecuritystart.entity.AppUser
import io.github.abitgen.springsecuritystart.repo.AppUserFindRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DevBootstrap @Autowired
    constructor(@Qualifier("mockAppUserRepo")
                private val userRepo: AppUserFindRepo,
                val passwordEncoder: PasswordEncoder
                ) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val user = AppUser(name = "admin", password = passwordEncoder.encode("pass123"))
        userRepo.save(user)
    }
}
