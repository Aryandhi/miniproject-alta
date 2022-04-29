package com.alta.miniprojectcheckpoint.repository;

import com.alta.miniprojectcheckpoint.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    @Query("SELECT d FROM Departement d WHERE d.name_departement = :name_departement")
    Departement findByName(@Param("name_departement")String name_departement);
}
