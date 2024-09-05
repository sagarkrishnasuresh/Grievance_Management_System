package com.grievance.repository;

import com.grievance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String email);
}
