package com.example.demo.controller;

import com.example.demo.entity.ApplicationUserEntity;
import com.example.demo.service.ApplicationUserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/students")
@AllArgsConstructor
public class StudentController {

  private final ApplicationUserService userService;

  @GetMapping
  public List<ApplicationUserEntity> findStudent() {
    return userService.findStudent();
  }
}
