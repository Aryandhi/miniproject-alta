package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.dto.RoleRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.model.Role;
import com.alta.miniprojectcheckpoint.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    // GET
    @GetMapping("")
    public List<Role> getAllRole(){
        return roleService.getAll();
    }

    // Get By Name
    @GetMapping("/{name_role}")
    public Role getByName(@PathVariable("name_role")String name_role){
        return roleService.getByName(name_role);
    }

    // POST
    @PostMapping("")
    public Role create(@RequestBody RoleRequest roleRequest) throws BadRequestException{
        // validasi
        roleService.validateRole(roleRequest);
        // save
        return roleService.save(roleRequest);
    }

    // PUT
    @PutMapping("/{name_role}")
    public Role update(@PathVariable("name_role") String name_role, @RequestBody RoleRequest roleRequest){
        // validasi
        roleService.validateRole(roleRequest);
        // update -> save
        Role searchByName = roleService.getByName(name_role);
        return roleService.update(roleRequest, searchByName);
    }

    // DELETE
    @DeleteMapping("/{id_role}")
    public void delete(@PathVariable("id_role")Integer id_role){
        roleService.delete(id_role);
    }
}
