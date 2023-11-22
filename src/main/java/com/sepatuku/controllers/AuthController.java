package com.sepatuku.controllers;

import com.sepatuku.dtos.MessageResponseDto;
import com.sepatuku.dtos.auth.LoginRequestDto;
import com.sepatuku.dtos.auth.RegisterRequestDto;
import com.sepatuku.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("signin")
    public ResponseEntity<MessageResponseDto> signin(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.signin(loginRequestDto));
    }

    @PostMapping("signup")
    public ResponseEntity<MessageResponseDto> signup(@RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(registerRequestDto));
    }

//    @PostMapping("signout")
//    public ResponseEntity<MessageResponseDto> signout(@RequestBody RegisterRequestDto registerRequestDto){
//        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDto));
//    }
}
