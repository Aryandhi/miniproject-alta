package com.alta.miniprojectcheckpoint.repository;

import com.alta.miniprojectcheckpoint.model.Employee;
import com.alta.miniprojectcheckpoint.model.Fitnote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnoteRepository extends JpaRepository<Fitnote, Long> {
//    @Query("SELECT d FROM Fitnote d WHERE d.status = :status")
//    Fitnote findByStatus(@Param("status")String status);
}
