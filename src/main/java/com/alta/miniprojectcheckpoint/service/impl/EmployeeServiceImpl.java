package com.alta.miniprojectcheckpoint.service.impl;

import com.alta.miniprojectcheckpoint.dto.EmployeeRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.exception.ResourceNotFound;
import com.alta.miniprojectcheckpoint.model.Departement;
import com.alta.miniprojectcheckpoint.model.Employee;
import com.alta.miniprojectcheckpoint.model.Role;
import com.alta.miniprojectcheckpoint.repository.DepartementRepository;
import com.alta.miniprojectcheckpoint.repository.EmployeeRepository;
import com.alta.miniprojectcheckpoint.repository.RoleRepository;
import com.alta.miniprojectcheckpoint.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private RoleRepository roleRepository;

    // validasi
    @Override
    public void validateEmployee(EmployeeRequest employeeRequest) throws BadRequestException {
        // name not null
        if(employeeRequest.getEmployeeName() == "") throw new BadRequestException("Nama Wajib diisi");
        // email not null
        if(employeeRequest.getEmail() == "")throw new BadRequestException("Email Wajib diisi");
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByNameEmployee(String employeeName) throws ResourceNotFound {
        Employee searchName = employeeRepository.findByName(employeeName);
        if(searchName == null)throw new ResourceNotFound("Sorry, Name : "+employeeName+" NOT FOUND");
        return searchName;
    }

    //  public Employee createNewEmployee(EmployeeRequest employeeRequest)
    @Override
    public Employee createNewEmployee(EmployeeRequest employeeRequest) {
        Employee result = new Employee();
        Optional<Departement> departement = departementRepository.findById(employeeRequest.getId_departement());
        Optional<Role> role = roleRepository.findById(employeeRequest.getId_role());

        result.setEmployeeName(employeeRequest.getEmployeeName());
        result.setEmail(employeeRequest.getEmail());
        result.setAddress(employeeRequest.getAddress());
        result.setNoTelp(employeeRequest.getNoTelp());
        // reference column
        result.setDepartement(departement.get());
        result.setRole(role.get());
        return employeeRepository.save(result);
    }

    @Override
    public Employee updateEmployee(EmployeeRequest employeeRequest, Employee employee) {
        employee.setEmployeeName(employeeRequest.getEmployeeName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setNoTelp(employee.getNoTelp());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id_employee) throws ResourceNotFound {
        try {
            Optional<Employee> searchById = employeeRepository.findById(id_employee);
            employeeRepository.delete(searchById.get());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
