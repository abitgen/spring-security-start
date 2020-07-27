package io.github.abitgen.springsecuritystart.repo

import io.github.abitgen.springsecuritystart.entity.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AppUserFindRepo : CrudRepository<AppUser, UUID>{

    @Query("select u from AppUser u where u.name=:username")
    fun findByName(username: String?) : Optional<AppUser>
}