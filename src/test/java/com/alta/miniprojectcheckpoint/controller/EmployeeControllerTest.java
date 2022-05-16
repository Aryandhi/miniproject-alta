package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.constant.AppConstant;
import com.alta.miniprojectcheckpoint.dto.DepartementRequest;
import com.alta.miniprojectcheckpoint.dto.EmployeeRequest;
import com.alta.miniprojectcheckpoint.service.impl.EmployeeServiceImpl;
import com.alta.miniprojectcheckpoint.util.ResponseUtilDepartement;
import com.alta.miniprojectcheckpoint.util.ResponseUtilEmployee;
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
@SpringBootTest(classes = EmployeeController.class)
@EnableWebMvc
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testAddEmployeeSuccess() throws Exception {
        when(employeeService.createNewEmployee(any()))
                .thenReturn(ResponseUtilEmployee.build(AppConstant.Message.SUCCESS, EmployeeRequest.builder()
                        .id_employee(1L)
                        .build(), HttpStatus.OK));
        this.mockMvc.perform(post("/api/employee")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testAddEmployeeException() {
        when(employeeService.createNewEmployee(any())).thenThrow(NullPointerException.class);

        Assertions.assertThrows(Exception.class, () -> this.mockMvc.perform(post("/api/employee")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}"))
                .andReturn());
    }

    @Test
    void testUpdateEmployeeSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.put("/api/employee/{employeeName}", "Agus")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployeeSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .delete("/api/employee/{id_employee}", 1)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllEmployeeSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/employee" )
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByNameEmployee() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/employee/{employeeName}", "Agus" )
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}