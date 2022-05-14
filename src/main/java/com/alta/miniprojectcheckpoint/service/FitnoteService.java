package com.alta.miniprojectcheckpoint.service;

import com.alta.miniprojectcheckpoint.dto.FitnoteRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.exception.ResourceNotFound;
import com.alta.miniprojectcheckpoint.model.Fitnote;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FitnoteService {
    // - validasi
    public void validatedFitnote(@RequestBody FitnoteRequest fitnoteRequest) throws BadRequestException;

    // - FindAll
    public List<Fitnote> findAllFitnote();

    // FindById
    public Fitnote findFitnoteById(Long id);

    // - Create
    public Fitnote createNewFitnote(FitnoteRequest fitnoteRequest);

    // - update / save
    public Fitnote updateFitnote(FitnoteRequest fitnoteRequest, Fitnote fitnote);

    // - delete
    public void deleteFitnote(Long id_fitnote) throws ResourceNotFound;
}
