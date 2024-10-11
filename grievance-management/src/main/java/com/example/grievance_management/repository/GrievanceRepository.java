package com.example.grievance_management.repository;

import com.example.grievance_management.model.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    List<Grievance> findBySupervisor_SupervisorId(Long supervisorId);
    List<Grievance> findByAssignee_AssigneeId(Long assigneeId);


    // Correct method to find grievances by the user's ID
    List<Grievance> findByUser_UserId(Long userId);
}
