package com.devart.helloevents.repository;

import com.devart.helloevents.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findUsersByuserNameOrEmail(String username, String email);

boolean existsByEmail(String email);

boolean existsByUserName(String userName);
}
