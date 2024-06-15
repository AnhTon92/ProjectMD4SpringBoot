package com.ra.projectspringboot.controller;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.dto.request.LoginRequest;
import com.ra.projectspringboot.dto.request.RegisterRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws CustomException {
        return ResponseEntity.ok().body(
                ResponseWrapper.builder()
                        .status(EHttpStatus.SUCCESS)
                        .code(200)
                        .data(authService.login(loginRequest))
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws CustomException {
        authService.register(registerRequest);
        return new ResponseEntity<>(
                ResponseWrapper.builder()
                        .status(EHttpStatus.SUCCESS)
                        .code(201)
                        .data("register successfully")
                        .build(),
                HttpStatus.CREATED);
    }

}
