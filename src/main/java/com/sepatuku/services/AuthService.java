package com.sepatuku.services;

import com.sepatuku.dtos.AuthResponseDto;
import com.sepatuku.dtos.auth.LoginRequestDto;
import com.sepatuku.dtos.auth.RegisterRequestDto;

public interface AuthService {
    AuthResponseDto authenticate(LoginRequestDto loginRequestDto);
    AuthResponseDto register(RegisterRequestDto registerRequestDto);
}
