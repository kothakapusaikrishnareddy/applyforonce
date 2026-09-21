package com.applyforonce.service;

import com.applyforonce.dto.RegisterRequest;
import com.applyforonce.dto.RegisterResponse;


public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);
}
