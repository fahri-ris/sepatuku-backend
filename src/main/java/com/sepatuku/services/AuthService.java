package com.sepatuku.services;

import com.sepatuku.dtos.MessageResponseDto;
import com.sepatuku.dtos.auth.LoginRequestDto;
import com.sepatuku.dtos.auth.RegisterRequestDto;

public interface AuthService {
    MessageResponseDto login(LoginRequestDto loginRequestDto);
    MessageResponseDto register(RegisterRequestDto registerRequestDto);
}
