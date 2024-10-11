package com.example.grievance_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonProperty("statusId")  // This will map the 'statusId' field in JSON
    private Long statusId;


    @JsonProperty("statusUpdate")  // This will map the 'statusUpdate' field in JSON
    private String statusUpdate;

    @OneToMany(mappedBy = "status")
    @JsonProperty("grievances")  // This will map the 'grievances' list in JSON
    private List<Grievance> grievances;
}
