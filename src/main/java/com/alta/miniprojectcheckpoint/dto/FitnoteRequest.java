package com.alta.miniprojectcheckpoint.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FitnoteRequest {
    private Long id_fitnote;
    private Double temperature;
    private Integer sleep_hour;
    private String note;
    private String status;

    private Long id_employee;
}
