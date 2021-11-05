package com.example.demo.service;

import static com.example.demo.applicationuser.ApplicationUserRole.STUDENT;

import com.example.demo.entity.ApplicationUserEntity;
import com.example.demo.repository.ApplicationUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationUserService implements UserDetailsService {

  private final ApplicationUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findUserByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format("Username %s not found", username)));
  }

  public ApplicationUserEntity registerUser(ApplicationUserEntity user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setCredentialsNonExpired(true);
    user.setEnabled(true);
    return userRepository.save(user);
  }

  public List<ApplicationUserEntity> findAllUsers() {
    return userRepository.findAll();
  }

  public ApplicationUserEntity findUserById(Long id) throws UsernameNotFoundException {
    return userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User %d not found", id)));
  }

  public List<ApplicationUserEntity> findStudent() throws UsernameNotFoundException {
    return userRepository.findUserByUserRole(STUDENT);
  }

}
