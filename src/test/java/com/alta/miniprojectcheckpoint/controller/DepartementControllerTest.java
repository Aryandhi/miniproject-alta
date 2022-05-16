package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.constant.AppConstant;
import com.alta.miniprojectcheckpoint.dto.DepartementRequest;
import com.alta.miniprojectcheckpoint.service.DepartementService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.alta.miniprojectcheckpoint.util.ResponseUtilDepartement;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(SpringExtension.class) // JUnit 4 @RunWith(SpringRunner.class)
@SpringBootTest(classes = DepartementController.class)
@EnableWebMvc
@AutoConfigureMockMvc
class DepartementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartementService departementService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testAddDepartementSuccess() throws Exception {
        when(departementService.save(any()))
                .thenReturn(ResponseUtilDepartement.build(AppConstant.Message.SUCCESS, DepartementRequest.builder()
                        .id_departement(1)
                        .build(), HttpStatus.OK));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/departements")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testAddDepartementException() {
        when(departementService.save(any())).thenThrow(NullPointerException.class);

        Assertions.assertThrows(Exception.class, () -> this.mockMvc.perform(post("/api/departements")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}"))
                .andReturn());
    }

    @Test
    void testUpdateDepartementSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.put("/api/departements/{name_departement}", "Digital Marketing")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteDepartementSucces()throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .delete("/api/departements/{id_departement}", 1)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllDepartementSuccess() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/departements" )
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByNameDepartementSuccess() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/departements/{name_departement}", "Digital Marketing" )
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}