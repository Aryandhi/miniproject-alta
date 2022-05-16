package com.alta.miniprojectcheckpoint.service.impl;

import com.alta.miniprojectcheckpoint.repository.UserRepository;
import com.alta.miniprojectcheckpoint.security.JwtProvider;
import com.alta.miniprojectcheckpoint.service.AuthService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthService.class)
class AuthServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtProvider jwtTokenProvider;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

}