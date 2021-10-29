package com.example.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith"));

    // hasRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents() {
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerStudent(@RequestBody Student student) {
        System.out.println("registerStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable Integer studentId) {
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(path = "/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
        System.out.println("updateStudent");
        System.out.printf("id: %d%nStudent: %s%n", studentId, student);
    }
}
