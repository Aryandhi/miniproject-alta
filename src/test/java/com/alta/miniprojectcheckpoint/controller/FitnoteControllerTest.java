package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.constant.AppConstant;
import com.alta.miniprojectcheckpoint.dto.DepartementRequest;
import com.alta.miniprojectcheckpoint.dto.FitnoteRequest;
import com.alta.miniprojectcheckpoint.service.DepartementService;
import com.alta.miniprojectcheckpoint.service.impl.FitnoteServiceImpl;
import com.alta.miniprojectcheckpoint.util.ResponseUtilDepartement;
import com.alta.miniprojectcheckpoint.util.ResponseUtilFitnote;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class) // JUnit 4 @RunWith(SpringRunner.class)
@SpringBootTest(classes = FitnoteController.class)
@EnableWebMvc
@AutoConfigureMockMvc
class FitnoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FitnoteServiceImpl fitnoteService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testAddFitnoteSuccess() throws Exception {
        when(fitnoteService.createNewFitnote(any()))
                .thenReturn(ResponseUtilFitnote.build(AppConstant.Message.SUCCESS, FitnoteRequest.builder()
                        .id_fitnote(1L)
                        .build(), HttpStatus.OK));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/fitnotes")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testAddFitnoteException() {
        when(fitnoteService.createNewFitnote(any())).thenThrow(NullPointerException.class);

        Assertions.assertThrows(Exception.class, () -> this.mockMvc.perform(post("/api/fitnotes")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}"))
                .andReturn());
    }

    @Test
    void testUpdateFitnoteSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.put("/api/fitnotes/{id}", 1)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllFitnoteSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/fitnotes" )
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteFitnoteSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .delete("/api/fitnotes/{id}", 1)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}