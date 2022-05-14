package com.alta.miniprojectcheckpoint.service;

import com.alta.miniprojectcheckpoint.dto.EmployeeRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.exception.ResourceNotFound;
import com.alta.miniprojectcheckpoint.model.Employee;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {
    // validasi
    public void validateEmployee(@RequestBody EmployeeRequest employeeRequest) throws BadRequestException;

    // Find
    public List<Employee> findAllEmployee();

    // Find by Name
    public Employee findByNameEmployee(String employeeName) throws ResourceNotFound;

    // Create
    public Employee createNewEmployee(EmployeeRequest employeeRequest);

    // Update
    public Employee updateEmployee(EmployeeRequest employeeRequest, Employee employee);

    // Delete
    public void deleteEmployee(Long id_employee) throws ResourceNotFound;
}
