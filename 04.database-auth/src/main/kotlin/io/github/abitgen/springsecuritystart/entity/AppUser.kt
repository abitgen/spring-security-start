package io.github.abitgen.springsecuritystart.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Version

@Entity
class AppUser(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @Version
        val version: Long? = null,

        val name: String,

        val password:String
) {

    override fun equals(other: Any?) = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        id != (other as AppUser).id -> false
        else -> true
    }

    override fun hashCode(): Int = id.hashCode()
}