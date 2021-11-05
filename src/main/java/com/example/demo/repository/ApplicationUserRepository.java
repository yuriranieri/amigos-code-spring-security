package com.example.demo.repository;

import com.example.demo.applicationuser.ApplicationUserRole;
import com.example.demo.entity.ApplicationUserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUserEntity, Long> {

  Optional<ApplicationUserEntity> findUserByUsername(String username);
  List<ApplicationUserEntity> findUserByUserRole(ApplicationUserRole userRole);
}
