package com.example.grievance_management.service;

import com.example.grievance_management.model.Status;
import com.example.grievance_management.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    public Status getStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }

    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
}
