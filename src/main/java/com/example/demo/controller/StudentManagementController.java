package com.example.demo.controller;

import com.example.demo.entity.StudentEntity;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "management/api/v1/students")
public class StudentManagementController {

  private static final List<StudentEntity> STUDENTS =
      List.of(
          new StudentEntity(1, "James Bond"),
          new StudentEntity(2, "Maria Jones"),
          new StudentEntity(3, "Anna Smith"));

  // hasRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
  public List<StudentEntity> getAllStudents() {
    return STUDENTS;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('student:write')")
  public void registerStudent(@RequestBody StudentEntity student) {
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
  public void updateStudent(@PathVariable Integer studentId, @RequestBody StudentEntity student) {
    System.out.println("updateStudent");
    System.out.printf("id: %d%nStudent: %s%n", studentId, student);
  }
}
