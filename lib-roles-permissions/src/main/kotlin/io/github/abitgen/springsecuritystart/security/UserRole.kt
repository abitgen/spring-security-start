package io.github.abitgen.springsecuritystart.security

import io.github.abitgen.springsecuritystart.security.UserPermission.*

enum class UserRole(val permissions: Set<UserPermission>) {

    STUDENT(hashSetOf<UserPermission>(COURSE_READ)),

    ADMIN(hashSetOf<UserPermission>(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),

    ADMINTRAINEE(hashSetOf<UserPermission>(COURSE_READ, STUDENT_READ))

}