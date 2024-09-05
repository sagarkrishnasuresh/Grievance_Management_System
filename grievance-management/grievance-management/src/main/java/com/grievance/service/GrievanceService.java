package com.grievance.service;

import com.grievance.model.Grievance;
import com.grievance.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    public Grievance createGrievance(Grievance grievance) {
        return grievanceRepository.save(grievance);
    }

    public Grievance updateGrievance(Grievance grievance) {
        return grievanceRepository.save(grievance);
    }

    public Grievance getGrievanceById(Long id) {
        return grievanceRepository.findById(id).orElse(null);
    }
}
