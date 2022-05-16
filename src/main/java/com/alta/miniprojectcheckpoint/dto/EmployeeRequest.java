package com.alta.miniprojectcheckpoint.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeRequest {
    private Long id_employee;
    private String employeeName;
    private String email;
    private String address;
    private String noTelp;

    private Integer id_departement;
    private Integer id_role;
}
