package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.dto.FitnoteRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.model.Fitnote;
import com.alta.miniprojectcheckpoint.service.impl.FitnoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fitnotes")
public class FitnoteController {
    @Autowired
    FitnoteServiceImpl fitnoteService;

    // - GET
    @GetMapping("")
    public List<Fitnote> get(){
        return fitnoteService.findAllFitnote();
    }

    // - Get ById
    @GetMapping("/{id}")
    public Fitnote findById(@PathVariable("id") Long id) {
        return fitnoteService.findFitnoteById(id);
    }

    // - POST
    @PostMapping("")
    public Fitnote create(@RequestBody FitnoteRequest fitnoteRequest)throws BadRequestException {
        //validasi
        fitnoteService.validatedFitnote(fitnoteRequest);
        //save
        return fitnoteService.createNewFitnote(fitnoteRequest);
    }

    // - PUT
    @PutMapping("/{id}")
    public Fitnote update(@PathVariable("id") Long id,@RequestBody FitnoteRequest fitnoteRequest) {
        // validasi
        fitnoteService.validatedFitnote(fitnoteRequest);
        // update - save
        Fitnote fitnoteById = fitnoteService.findFitnoteById(id);
        return fitnoteService.updateFitnote(fitnoteRequest, fitnoteById);
    }

    // - delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        fitnoteService.deleteFitnote(id);
    }

}
