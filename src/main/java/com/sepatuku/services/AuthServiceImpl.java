package com.sepatuku.services;

import com.sepatuku.dtos.MessageResponseDto;
import com.sepatuku.dtos.auth.LoginRequestDto;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public MessageResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }
}
