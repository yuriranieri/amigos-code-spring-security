package com.example.demo.applicationuser;

import static com.example.demo.applicationuser.ApplicationUserPermission.COURSE_READ;
import static com.example.demo.applicationuser.ApplicationUserPermission.COURSE_WRITE;
import static com.example.demo.applicationuser.ApplicationUserPermission.STUDENT_READ;
import static com.example.demo.applicationuser.ApplicationUserPermission.STUDENT_WRITE;

import com.google.common.collect.Sets;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
@Getter
public enum ApplicationUserRole {
  STUDENT(Sets.newHashSet(), "STUDENT"),
  ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE), "ADMIN"),
  ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ), "ADMINTRAINEE");

  private final Set<ApplicationUserPermission> permissions;
  private final String name;

  public static Optional<ApplicationUserRole> from(String name) {
    return Stream.of(values())
        .filter(role -> role.getName().equals(name))
        .findFirst();
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions =
        getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return permissions;
  }
}
