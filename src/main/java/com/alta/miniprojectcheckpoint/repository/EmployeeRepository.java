package com.alta.miniprojectcheckpoint.repository;

import com.alta.miniprojectcheckpoint.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT d FROM Employee d WHERE d.employeeName = :employeeName")
    Employee findByName(@Param("employeeName")String employeeName);
}
