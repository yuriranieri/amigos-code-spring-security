package com.example.demo.auth;

import static com.example.demo.security.ApplicationUserRole.ADMIN;
import static com.example.demo.security.ApplicationUserRole.ADMINTRAINEE;
import static com.example.demo.security.ApplicationUserRole.STUDENT;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("fake")
@RequiredArgsConstructor
public class FakeApplicationUserDaoService implements ApplicationUserDao {

  private final PasswordEncoder passwordEncoder;

  @Override
  public Optional<ApplicationUser> findUserByUsername(String username) {
    return getApplicationUsers().stream()
        .filter(user -> username.equals(user.getUsername()))
        .findFirst();
  }

  private List<ApplicationUser> getApplicationUsers() {
    return List.of(
        new ApplicationUser(
            "annasmith",
            passwordEncoder.encode("password"),
            STUDENT.getGrantedAuthorities(),
            true,
            true,
            true,
            true),
        new ApplicationUser(
            "linda",
            passwordEncoder.encode("password"),
            ADMIN.getGrantedAuthorities(),
            true,
            true,
            true,
            true),
        new ApplicationUser(
            "tom",
            passwordEncoder.encode("password"),
            ADMINTRAINEE.getGrantedAuthorities(),
            true,
            true,
            true,
            true));
  }
}
