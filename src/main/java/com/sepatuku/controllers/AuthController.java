package com.sepatuku.controllers;

import com.sepatuku.dtos.AuthResponseDto;
import com.sepatuku.dtos.auth.LoginRequestDto;
import com.sepatuku.dtos.auth.RegisterRequestDto;
import com.sepatuku.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.authenticate(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDto));
    }

//    @PostMapping("signout")
//    public ResponseEntity<MessageResponseDto> signout(@RequestBody RegisterRequestDto registerRequestDto){
//        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDto));
//    }
}
