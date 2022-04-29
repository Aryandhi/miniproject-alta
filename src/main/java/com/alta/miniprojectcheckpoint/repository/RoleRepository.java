package com.alta.miniprojectcheckpoint.repository;

import com.alta.miniprojectcheckpoint.model.Departement;
import com.alta.miniprojectcheckpoint.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT d FROM Role d WHERE d.name_role = :name_role")
    Role findByName(@Param("name_role")String name_role);
}
