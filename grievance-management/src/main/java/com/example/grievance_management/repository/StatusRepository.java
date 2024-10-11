package com.example.grievance_management.repository;

import com.example.grievance_management.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    // Custom query methods (if needed) can be added here
}
