package io.github.abitgen.springsecuritystart.controller

import io.github.abitgen.springsecuritystart.data.Constant.Companion.Student_List
import io.github.abitgen.springsecuritystart.model.Student
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/management/v1/students")
class ManagementController {

    @GetMapping()
    fun getAllStudents(): List<Student> {
        println("Fetching all Students")
        return Student_List
    }

    @PostMapping()
    fun registerStudent(): String {
        println("saving Student")
        return "Student Saved "
    }

    @PutMapping(path = ["studentId"])
    fun updateStudent(@PathVariable("studentId") id: Int): String {
        println("updating Student $id")
        return "Student Updated "
    }

    @DeleteMapping(path = ["studentId"])
    fun deleteStudent(@PathVariable("studentId") id: Int): String {
        println("deleting Student $id")
        return "Student Deleted "
    }

}