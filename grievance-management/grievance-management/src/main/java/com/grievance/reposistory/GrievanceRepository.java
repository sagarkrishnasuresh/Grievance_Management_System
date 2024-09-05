package com.grievance.repository;

import com.grievance.model.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
}
