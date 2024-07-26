package com.devart.helloevents.repository;

import com.devart.helloevents.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findUsersByuserNameOrEmail(String username, String email);

boolean existsByEmail(String email);

boolean existsByUserName(String userName);

   Optional<User> findByUserName(String userName);
}
