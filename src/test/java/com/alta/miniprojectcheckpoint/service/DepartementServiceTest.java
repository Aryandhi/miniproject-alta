package com.alta.miniprojectcheckpoint.service;

import com.alta.miniprojectcheckpoint.dto.DepartementRequest;
import com.alta.miniprojectcheckpoint.model.Departement;
import com.alta.miniprojectcheckpoint.repository.DepartementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DepartementService.class)
class DepartementServiceTest {

    @MockBean
    private DepartementRepository departementRepository;

//    @MockBean
//    private ModelMapper modelMapper;

    @InjectMocks
    private DepartementService departementService;

    private Departement departement;

    @BeforeEach
    void setUp() {
        departement = Departement.builder()
                .id_departement(1)
                .name_departement("Digital Marketing")
                .build();
    }

    @Test
    void testSaveWith() {

    }

    @Test
    void testGetAllSuccess(){
//        departementService.getALL();
//        verify(departementRepository).findAll();

    }
}