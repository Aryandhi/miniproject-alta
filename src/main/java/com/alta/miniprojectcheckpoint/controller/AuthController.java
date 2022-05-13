package com.alta.miniprojectcheckpoint.controller;

import com.alta.miniprojectcheckpoint.payload.TokenResponse;
import com.alta.miniprojectcheckpoint.payload.UsernamePassword;
import com.alta.miniprojectcheckpoint.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsernamePassword req){
        authService.register(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody UsernamePassword usernamePassword){
        TokenResponse token = authService.generateToken(usernamePassword);
        return ResponseEntity.ok(token);
    }
}
