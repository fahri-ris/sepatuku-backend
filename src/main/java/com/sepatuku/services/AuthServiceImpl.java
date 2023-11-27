package com.sepatuku.services;

import com.sepatuku.config.JwtService;
import com.sepatuku.dtos.AuthResponseDto;
import com.sepatuku.dtos.auth.LoginRequestDto;
import com.sepatuku.dtos.auth.RegisterRequestDto;
import com.sepatuku.models.Role;
import com.sepatuku.models.Users;
import com.sepatuku.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {
        Users users = Users.builder()
                .username(registerRequestDto.getUsername())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .build();
        usersRepository.save(users);
        String jwtToken = jwtService.generateToken(users);

        AuthResponseDto authResponseDto = AuthResponseDto.builder()
                .token(jwtToken)
                .build();
        return authResponseDto;
    }

    @Override
    public AuthResponseDto authenticate(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        Users users = usersRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not Found"));

        String jwtToken = jwtService.generateToken(users);

        AuthResponseDto authResponseDto = AuthResponseDto.builder()
                .token(jwtToken)
                .build();
        return authResponseDto;
    }


}
