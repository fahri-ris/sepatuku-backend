package com.sepatuku.controllers;

import com.sepatuku.dtos.MessageResponseDto;
import com.sepatuku.dtos.auth.LoginRequestDto;
import com.sepatuku.dtos.auth.RegisterRequestDto;
import com.sepatuku.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDto));
    }
}
