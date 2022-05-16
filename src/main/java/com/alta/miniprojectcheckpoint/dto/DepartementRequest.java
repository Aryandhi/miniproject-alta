package com.alta.miniprojectcheckpoint.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class DepartementRequest {
    private Integer id_departement;
    private String name_departement;
}
