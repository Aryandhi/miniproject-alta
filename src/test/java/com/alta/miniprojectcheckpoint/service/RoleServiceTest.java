package com.alta.miniprojectcheckpoint.service;

import com.alta.miniprojectcheckpoint.constant.AppConstant;
import com.alta.miniprojectcheckpoint.domain.common.ApiResponse;
import com.alta.miniprojectcheckpoint.dto.RoleRequest;
import com.alta.miniprojectcheckpoint.model.Role;
import com.alta.miniprojectcheckpoint.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RoleService.class)
class RoleServiceTest {

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;

    @Test
    void testAddRoleServiceSuccess() {
        Role role = Role.builder()
                .id_role(1)
                .build();

        RoleRequest roleRequest = RoleRequest.builder()
                .id_role(1)
                .name_role("Backend Developer")
                .build();
        when(modelMapper.map(any(), eq(Role.class))).thenReturn(role);
        when(modelMapper.map(any(), eq(RoleRequest.class))).thenReturn(roleRequest);
        when(roleRepository.save(any())).thenReturn(role);

        Role responseRoleEntity = roleService.save(RoleRequest.builder()
                .name_role("Backend Developer")
                .build());
    }

    @Test
    void testAddRoleException() {
        Role role = Role.builder()
                .id_role(1)
                .name_role("Backend Developer")
                .build();

        when(roleRepository.findAll()).thenReturn(List.of(role));
        when(modelMapper.map(any(), eq(RoleRequest.class))).thenReturn(RoleRequest.builder()
                .id_role(1)
                .name_role("Backend Developer")
                .build());
//        Role responseEntity = (Role) roleService.getAll();

//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();

//        List<RoleRequest> roleDto = (List<RoleRequest>) apiResponse.getData();
//
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(AppConstant.Message.SUCCESS, Objects.requireNonNull(apiResponse).getMessage());
//        assertEquals(1, roleDto.size());
    }

    @Test
    void testGetAllRoleException() {
        when(roleRepository.findAll()).thenThrow(NullPointerException.class);
        assertThrows(Exception.class, () -> roleService.getAll());
    }

    @Test
    void testGetByIdRoleSuccess() {
        Role role = Role.builder()
                .id_role(1)
                .name_role("Backend Developer")
                .build();
        when(roleRepository.findById((int) anyLong())).thenReturn(Optional.of(role));
        when(modelMapper.map(any(), eq(RoleRequest.class))).thenReturn(RoleRequest.builder()
                .id_role(1)
                .name_role("Backend Developer")
                .build());
    }

    @Test
    void testGetByIdRoleNotFound() {
        when(roleRepository.findById((int) anyLong())).thenReturn(Optional.empty());
    }

    @Test
    void testDeleteRoleSuccess() {
        when(roleRepository.findById((int) anyLong())).thenReturn(Optional.of(Role.builder()
                .id_role(1)
                .name_role("Backend Developer")
                .build()));
        doNothing().when(roleRepository).delete(any());
    }

    @Test
    void testUpdateRoleSuccess() {
        Role role = Role.builder()
                .id_role(1)
                .build();

        RoleRequest roleRequest = RoleRequest.builder()
                .id_role(1)
                .name_role("Backend Developer")
                .build();
        when(roleRepository.findById((int) anyLong())).thenReturn(Optional.of(role));
        when(modelMapper.map(any(), eq(Role.class))).thenReturn(role);
        when(modelMapper.map(any(), eq(RoleRequest.class))).thenReturn(roleRequest);
    }
}