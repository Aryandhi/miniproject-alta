package com.alta.miniprojectcheckpoint.service;

import com.alta.miniprojectcheckpoint.dto.DepartementRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.exception.ResourceNotFound;
import com.alta.miniprojectcheckpoint.model.Departement;
import com.alta.miniprojectcheckpoint.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartementService {
    @Autowired
    DepartementRepository departementRepository;

    // validasi
    public void validateDepartement(@RequestBody DepartementRequest departementRequest)throws BadRequestException{
        // name tidak boleh kosong
        if( departementRequest.getName_departement() == "")throw new BadRequestException("Field departement harus diisi");
    }

    // Get
    public List<Departement> getALL(){
        return departementRepository.findAll();
    }

    // GetById
//    public Optional<Departement> getById(Integer id) throws ResourceNotFound {
//        Optional<Departement> departement = departementRepository.findById(id);
//        if(departement == null) throw new ResourceNotFound("Id : "+id+" tidak ditemukan");
//        return departement;
//    }

    // GetByName
    public Departement getByName(String name_departement)throws ResourceNotFound{
        Departement searchDepartementByName = departementRepository.findByName(name_departement);
        if(searchDepartementByName == null) throw new ResourceNotFound("Name : "+name_departement+" tidak ditemukan");
        return searchDepartementByName;
    }

    // Create
    public Departement save(DepartementRequest departementRequest){
        Departement result = new Departement();
        result.setName_departement(departementRequest.getName_departement());
        return departementRepository.save(result);
    }

    // Update
    public Departement update(DepartementRequest departementRequest, Departement departement){
        departement.setName_departement(departementRequest.getName_departement());
        return departementRepository.save(departement);
    }

    // Delete
    public void delete(Integer id) throws ResourceNotFound{
        try {
           Optional<Departement> searchDepartement = departementRepository.findById(id);
           departementRepository.delete(searchDepartement.get());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
