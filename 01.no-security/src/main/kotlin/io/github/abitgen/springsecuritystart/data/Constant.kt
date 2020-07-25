package io.github.abitgen.springsecuritystart.data

import io.github.abitgen.springsecuritystart.model.Student

class Constant {
    companion object {
        val Student_List = listOf<Student>(
                Student(1, "Sam"),
                Student(2, "tim"),
                Student(3,"Jane")
        )
    }
}