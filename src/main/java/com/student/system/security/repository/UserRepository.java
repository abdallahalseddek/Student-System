package com.student.system.security.repository;

import com.student.system.security.entity.User;
import com.student.system.security.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);

}
