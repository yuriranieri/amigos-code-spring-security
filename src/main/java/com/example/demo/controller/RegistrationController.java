package com.example.demo.controller;

import com.example.demo.entity.ApplicationUserEntity;
import com.example.demo.registration.UserRegistrationRequest;
import com.example.demo.service.RegistrationService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ApplicationUserEntity registerUser(@RequestBody @Valid UserRegistrationRequest userPayload) {
    return registrationService.registerUser(userPayload);
  }

  @GetMapping(path = "/users")
  @ResponseStatus(HttpStatus.OK)
  public List<ApplicationUserEntity> findAllUsers() {
    return registrationService.findAllUsers();
  }

  @GetMapping(path = "/users/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApplicationUserEntity findByUsername(@PathVariable Long id) {
    return registrationService.findById(id);
  }

}
