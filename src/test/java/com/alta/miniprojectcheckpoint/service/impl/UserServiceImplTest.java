package com.alta.miniprojectcheckpoint.service.impl;

import com.alta.miniprojectcheckpoint.model.User;
import com.alta.miniprojectcheckpoint.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserServiceImpl.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void testLoadUser() {
        User user = User.builder()
                .id(1L)
                .username("Arief")
                .build();
        when(userRepository.getDistinctTopByUsername(any())).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername(any());

        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test
    void testLoadUserNotFound() {
        when(userRepository.getDistinctTopByUsername(any())).thenReturn(null);
        assertThrows(Exception.class, () -> userService.loadUserByUsername(any()));
    }
}