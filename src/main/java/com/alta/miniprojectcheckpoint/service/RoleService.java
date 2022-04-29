package com.alta.miniprojectcheckpoint.service;

import com.alta.miniprojectcheckpoint.dto.RoleRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.exception.ResourceNotFound;
import com.alta.miniprojectcheckpoint.model.Role;
import com.alta.miniprojectcheckpoint.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    // validasi
    public void validateRole(@RequestBody RoleRequest roleRequest) throws BadRequestException{
        // name tidak boleh kosong
        if(roleRequest.getName_role() == "") throw new BadRequestException("Field Role wajib diisi");
    }

    // GET
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    // GET By Id
    public Optional<Role> getById(Integer id)throws ResourceNotFound{
        Optional<Role> searchById = roleRepository.findById(id);
        if(searchById == null) throw new ResourceNotFound("Id : "+id+" tidak ditemukan");
        return searchById;
    }

    // GetByName
    public Role getByName(String name_role)throws ResourceNotFound{
        Role searchByName = roleRepository.findByName(name_role);
        if(searchByName == null) throw new ResourceNotFound("Role : "+name_role+" tidak ditemukan");
        return searchByName;
    }

    // CREATE
    public Role save(RoleRequest roleRequest){
        Role result = new Role();
        result.setName_role(roleRequest.getName_role());
        return roleRepository.save(result);
    }

    // Update
    public Role update(RoleRequest roleRequest, Role role){
        role.setName_role(roleRequest.getName_role());
        return roleRepository.save(role);
    }

    // Delete
    public void delete(Integer id) throws ResourceNotFound{
        try {
            Optional<Role> searchById = roleRepository.findById(id);
            roleRepository.delete(searchById.get());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
