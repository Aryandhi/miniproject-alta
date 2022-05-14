package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.dto.EmployeeRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.model.Employee;
import com.alta.miniprojectcheckpoint.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;

    // GET
    @GetMapping("")
    public List<Employee> get(){
        return employeeService.findAllEmployee();
    }

    // GET by Name
    @GetMapping("/{employeeName}")
    public Employee getByName(@PathVariable("employeeName") String employeeName) {
        return employeeService.findByNameEmployee(employeeName);
    }

    //POST
    @PostMapping("")
    public Employee postDataEmployee(@RequestBody EmployeeRequest employeeRequest) throws BadRequestException {
        // validasi
        employeeService.validateEmployee(employeeRequest);
        // save
        return employeeService.createNewEmployee(employeeRequest);
    }

    // PUT
    @PutMapping("/{employeeName}")
    public Employee putDataEmployee(@PathVariable("employeeName")String employeeName, @RequestBody EmployeeRequest employeeRequest) {
        // validasi
        employeeService.validateEmployee(employeeRequest);
        // update - save
        Employee employeeByName = employeeService.findByNameEmployee(employeeName);
        return employeeService.updateEmployee(employeeRequest, employeeByName);

    }

    // DELETE
    @DeleteMapping("/{id_employee}")
    public void delete(@PathVariable("id_employee")Long id_employee){
        employeeService.deleteEmployee(id_employee);
    }
}
