package com.example.demo.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationRequest {

  @NotBlank(message = "username is mandatory")
  private final String username;

  @NotBlank(message = "password is mandatory")
  private final String password;

  @JsonProperty("user_role")
  @NotBlank(message = "userRole is mandatory")
  private final String userRole;

}
