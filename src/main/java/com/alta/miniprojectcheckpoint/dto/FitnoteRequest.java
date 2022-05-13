package com.alta.miniprojectcheckpoint.dto;

import lombok.Data;

@Data
public class FitnoteRequest {
    private Double temperature;
    private Integer sleep_hour;
    private String note;

    private Long id_employee;
}
