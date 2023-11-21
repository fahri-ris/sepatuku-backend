package com.sepatuku.services;

import com.sepatuku.dtos.MessageResponseDto;
import com.sepatuku.dtos.auth.LoginRequestDto;
import com.sepatuku.dtos.auth.RegisterRequestDto;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public MessageResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public MessageResponseDto register(RegisterRequestDto registerRequestDto) {
        return null;
    }
}
