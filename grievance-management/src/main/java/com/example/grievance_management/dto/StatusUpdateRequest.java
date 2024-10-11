package com.example.grievance_management.dto;

public class StatusUpdateRequest {
    private Long statusId;

    // Constructor
    public StatusUpdateRequest() {}

    // Getter and Setter
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
