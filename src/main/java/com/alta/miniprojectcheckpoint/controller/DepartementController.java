package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.dto.DepartementRequest;
import com.alta.miniprojectcheckpoint.model.Departement;
import com.alta.miniprojectcheckpoint.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/departements")
public class DepartementController {
    @Autowired
    DepartementService departementService;

    // GET
    @GetMapping("")
    public List<Departement> getAllDepartement(){
        return departementService.getALL();
    }

    // GET By Name
    @GetMapping("/{name_departement}")
    public Departement getByName(@PathVariable("name_departement")String name_departement){
        return departementService.getByName(name_departement);
    }

    // POST
//    @PostMapping("")
//    public Departement create(@RequestBody DepartementRequest departementRequest) throws BadRequestException{
//        // validasi
//        departementService.validateDepartement(departementRequest);
//        // save
//        return departementService.save(departementRequest);
//    }
    @PostMapping("")
    public Departement create(@RequestBody DepartementRequest departementRequest){
        try {
            // validasi
            departementService.validateDepartement(departementRequest);
            // save
            return departementService.save(departementRequest);
        } catch (Exception e){
            throw e;
        }
    }

    // PUT
    @PutMapping("/{name_departement}")
    public Departement update(@PathVariable("name_departement")String name_departement, @RequestBody DepartementRequest departementRequest){
        // validasi
        departementService.validateDepartement(departementRequest);
        // update -> save
        Departement searchByName = departementService.getByName(name_departement);
        return departementService.update(departementRequest, searchByName);
    }

    // DELETE
    @DeleteMapping("/{id_departement}")
    public void delete(@PathVariable("id_departement")Integer id_departement){
        departementService.delete(id_departement);
    }

}
