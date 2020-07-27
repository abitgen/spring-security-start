package io.github.abitgen.springsecuritystart.dev

import io.github.abitgen.springsecuritystart.entity.AppUser

class Constant {
    companion object {
        val User_List = listOf<AppUser>(
                AppUser(name = "admin", password = "345"),
                AppUser( name = "admint", password = "dfd"),
                AppUser(name = "studA",password = "ert")
        )


    }
}