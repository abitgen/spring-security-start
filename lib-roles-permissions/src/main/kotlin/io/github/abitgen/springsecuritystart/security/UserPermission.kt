package io.github.abitgen.springsecuritystart.security

enum class UserPermission( name: String) {

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),

    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),

}