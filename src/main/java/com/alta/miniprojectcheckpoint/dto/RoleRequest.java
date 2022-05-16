package com.alta.miniprojectcheckpoint.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleRequest {
    private Integer id_role;
    private String name_role;
}
