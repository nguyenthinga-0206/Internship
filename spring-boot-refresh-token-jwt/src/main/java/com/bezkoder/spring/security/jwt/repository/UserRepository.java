package com.bezkoder.spring.security.jwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Boolean existsByFirstnameAndLastname(String firstname, String lastname);

  Boolean existsByEmail(String email);

  Optional<User> findByPhone(String phone);

  Boolean existsByPhone(String phone);

  List<User> findByDeletedFlagFalse();
}
