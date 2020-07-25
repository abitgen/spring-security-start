package io.github.abitgen.springsecuritystart.controller

import io.github.abitgen.springsecuritystart.data.Constant.Companion.Student_List
import io.github.abitgen.springsecuritystart.model.Student
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/students")
class StudentController {



    @GetMapping(path = ["{studentId}"])
    fun getStudent(@PathVariable("studentId") id:Int): Student? {
        return Student_List.stream().filter { it.id ==  id}
                    .findFirst()
                    .orElseThrow { IllegalAccessException("Student record for id $id not found") }
    }
}