package com.example.demo.service;

import static com.example.demo.applicationuser.ApplicationUserRole.from;

import com.example.demo.entity.ApplicationUserEntity;
import com.example.demo.applicationuser.ApplicationUserRole;
import com.example.demo.registration.UserRegistrationRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

  private static final String USER_ROLE_DOES_NOT_EXIST = "user role %s does not exist";
  private final ApplicationUserService userService;

  public ApplicationUserEntity findById(Long id) {
    return userService.findUserById(id);
  }

  public List<ApplicationUserEntity> findAllUsers() {
    return userService.findAllUsers();
  }

  public ApplicationUserEntity registerUser(UserRegistrationRequest userPayload) {
    return userService.registerUser(toEntity(userPayload));
  }

  private ApplicationUserEntity toEntity(UserRegistrationRequest userPayload) {
    return new ApplicationUserEntity(userPayload.getUsername(),
        userPayload.getPassword(), getUserRole(userPayload.getUserRole().toUpperCase()));
  }

  private ApplicationUserRole getUserRole(String userRole) {
    return from(userRole)
        .orElseThrow(
            () -> new IllegalStateException(String.format(USER_ROLE_DOES_NOT_EXIST, userRole)));
  }

}
