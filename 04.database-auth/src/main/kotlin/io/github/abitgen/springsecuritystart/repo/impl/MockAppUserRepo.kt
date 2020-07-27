package io.github.abitgen.springsecuritystart.repo.impl

import io.github.abitgen.springsecuritystart.dev.Constant.Companion.User_List
import io.github.abitgen.springsecuritystart.entity.AppUser
import io.github.abitgen.springsecuritystart.repo.AppUserFindRepo
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.*

@Component("mockAppUserRepo")
class MockAppUserRepo : AppUserFindRepo {

    internal var dataMap : MutableMap<UUID, AppUser> = HashMap()

    override fun <S : AppUser> save(entity: S): S {
        dataMap[entity.id] = entity
        return entity
    }


    override fun findAll(): MutableList<AppUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteById(id: UUID) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun deleteAll(entities: MutableIterable<AppUser>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <S : AppUser?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun count(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAllById(ids: MutableIterable<UUID>): MutableList<AppUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun existsById(id: UUID): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findById(id: UUID): Optional<AppUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(entity: AppUser) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findByName(username: String?): Optional<AppUser> {

        val user = dataMap.values.stream().filter { it.name == username}
                .findFirst()

        return user
    }
}