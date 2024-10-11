package com.example.grievance_management.repository;

import com.example.grievance_management.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    // Custom method to find supervisor by email
    Supervisor findBySupervisorEmail(String supervisorEmail);
}
