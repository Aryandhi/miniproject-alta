package com.alta.miniprojectcheckpoint.service;

import com.alta.miniprojectcheckpoint.model.User;
import com.alta.miniprojectcheckpoint.payload.TokenResponse;
import com.alta.miniprojectcheckpoint.payload.UsernamePassword;

public interface AuthService {
    User register (UsernamePassword req);
    TokenResponse generateToken(UsernamePassword req);
}
