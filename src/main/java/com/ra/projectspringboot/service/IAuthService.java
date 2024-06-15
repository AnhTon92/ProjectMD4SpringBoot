package com.ra.projectspringboot.service;

import com.ra.projectspringboot.dto.request.LoginRequest;
import com.ra.projectspringboot.dto.request.RegisterRequest;
import com.ra.projectspringboot.dto.response.JwtResponse;
import com.ra.projectspringboot.exception.CustomException;

public interface IAuthService {
    JwtResponse login(LoginRequest loginRequest) throws CustomException;

    void register(RegisterRequest registerRequest) throws CustomException;
}
