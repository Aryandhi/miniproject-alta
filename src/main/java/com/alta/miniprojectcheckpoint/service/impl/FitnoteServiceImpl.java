package com.alta.miniprojectcheckpoint.service.impl;

import com.alta.miniprojectcheckpoint.dto.FitnoteRequest;
import com.alta.miniprojectcheckpoint.exception.BadRequestException;
import com.alta.miniprojectcheckpoint.exception.ResourceNotFound;
import com.alta.miniprojectcheckpoint.model.Employee;
import com.alta.miniprojectcheckpoint.model.Fitnote;
import com.alta.miniprojectcheckpoint.repository.EmployeeRepository;
import com.alta.miniprojectcheckpoint.repository.FitnoteRepository;
import com.alta.miniprojectcheckpoint.service.FitnoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnoteServiceImpl implements FitnoteService {

    @Autowired
    private FitnoteRepository fitnoteRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void validatedFitnote(FitnoteRequest fitnoteRequest) throws BadRequestException {
        // cek input suhu tubuh < 35 karena gejala hipotermia
        if(fitnoteRequest.getTemperature() < 35 )throw new BadRequestException("Suhu tubuhmu sangat rendah, mohon segera hubungi tenaga medis");
        // cek input suhu tubuh > 42 karena gejala hipertermia
        if(fitnoteRequest.getTemperature() > 42)throw new BadRequestException("Suhu tubuhmu sangat tinggi, mohon segera hubungi tenaga medis");
        // cek input jam tidur
        if(fitnoteRequest.getSleep_hour() < 2) throw new BadRequestException("Waktu tidurmu kurang, sebaiknya kamu istirahat agar besok lebih produktif");
    }

    @Override
    public List<Fitnote> findAllFitnote() {
        return fitnoteRepository.findAll();
    }

    @Override
    public Fitnote findFitnoteById(Long id_fitnote) {
        Optional<Fitnote> fitnote = fitnoteRepository.findById(id_fitnote);
        if (fitnote.isEmpty()) throw new ResourceNotFound("id : " + id_fitnote + " Tidak Ditemukan" );
        return fitnote.get();
    }

    @Override
    public Fitnote createNewFitnote(FitnoteRequest fitnoteRequest) {
        Fitnote fitnote = new Fitnote();
        Optional<Employee> employeeId = employeeRepository.findById(fitnoteRequest.getId_employee());
        // set fitnote
        fitnote.setTemperature(fitnoteRequest.getTemperature());
        fitnote.setSleep_hour(fitnoteRequest.getSleep_hour());
        // logic
        if(fitnoteRequest.getTemperature() < 36 && fitnoteRequest.getSleep_hour() < 4){
            fitnote.setNote("Suhu mu rendah dan terlihat kurang tidur, kamu harus segera istirahat");
        } else if (fitnoteRequest.getTemperature() > 38 && fitnoteRequest.getSleep_hour() < 4){
            fitnote.setNote("Ternyata kamu Demam dan terlihat kurang tidur, kamu harus segera istirahat");
        } else {
            fitnote.setNote("Kesehatan adalah aset berharga, Selamat Berkerja !");
        }
        // set Employee
        fitnote.setEmployee(employeeId.get());
        return fitnoteRepository.save(fitnote);
    }

    @Override
    public Fitnote updateFitnote(FitnoteRequest fitnoteRequest, Fitnote fitnote) {
        fitnote.setTemperature(fitnoteRequest.getTemperature());
        fitnote.setSleep_hour(fitnoteRequest.getSleep_hour());
//        fitnote.setNote(fitnoteRequest.getNote());
        // logic
        if(fitnoteRequest.getTemperature() < 36 && fitnoteRequest.getSleep_hour() < 4){
            fitnote.setNote("Suhu mu rendah dan terlihat kurang tidur, kamu harus segera istirahat");
        } else if (fitnoteRequest.getTemperature() > 38 && fitnoteRequest.getSleep_hour() < 4){
            fitnote.setNote("Ternyata kamu Demam dan terlihat kurang tidur, kamu harus segera istirahat");
        } else {
            fitnote.setNote("Kesehatan adalah aset berharga, Selamat Berkerja !");
        }
        return fitnoteRepository.save(fitnote);
    }

    @Override
    public void deleteFitnote(Long id_fitnote) throws ResourceNotFound {
        try {
            Optional<Fitnote> tran = fitnoteRepository.findById(id_fitnote);
            fitnoteRepository.delete(tran.get());
        } catch (Exception e) {
            throw new ResourceNotFound("Gagal Delete, fitNoteId: " + id_fitnote + " tidak ditemukan");
        }
    }
}
