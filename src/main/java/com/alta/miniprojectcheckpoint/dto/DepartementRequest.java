package com.alta.miniprojectcheckpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartementRequest {
    private Integer id_departement;
    private String name_departement;
}
