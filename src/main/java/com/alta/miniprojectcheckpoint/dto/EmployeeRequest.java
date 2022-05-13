package com.alta.miniprojectcheckpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeRequest {
    private String employeeName;
    private String email;
    private String address;
    private String noTelp;

    private Integer id_departement;
    private Integer id_role;
}
