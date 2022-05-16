package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.constant.AppConstant;
import com.alta.miniprojectcheckpoint.dto.RoleRequest;
import com.alta.miniprojectcheckpoint.service.RoleService;
import com.alta.miniprojectcheckpoint.util.ResponseUtilRole;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class) // JUnit 4 @RunWith(SpringRunner.class)
@SpringBootTest(classes = RoleController.class)
@EnableWebMvc
@AutoConfigureMockMvc
class RoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testAddRoleSuccess() throws Exception {
        when(roleService.save(any()))
                .thenReturn(ResponseUtilRole.build(AppConstant.Message.SUCCESS, RoleRequest.builder()
                .id_role(1)
                .build(), HttpStatus.OK));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/role")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testAddRoleExceptions() {
        when(roleService.save(any())).thenThrow(NullPointerException.class);
        Assertions.assertThrows(Exception.class, () -> this.mockMvc.perform(post("/api/role")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{}"))
                .andReturn());
    }

    @Test
    void testDeleteRoleSuccess() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                        .delete("/api/role/{id_role}", 1)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllRoleSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/role" )
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByNameRoleSuccess() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/role/{name_role}", "Digital Marketing" )
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}