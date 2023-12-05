package com.sepatuku.controllers;

import com.sepatuku.dtos.LoginRequestDto;
import com.sepatuku.dtos.MessageResponseDto;
import com.sepatuku.dtos.RegisterRequestDto;
import com.sepatuku.models.Role;
import com.sepatuku.models.UserEntity;
import com.sepatuku.repositories.RoleRepository;
import com.sepatuku.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        MessageResponseDto responseDto = null;
        if(userRepository.existsByUsername(registerRequestDto.getUsername())){
            responseDto.setMessage("Username sudah dipakai, Gunakan username lain");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setEnabled(true);

        Role role = roleRepository.findByRoleName("USER")
                        .orElseThrow(() -> new RuntimeException("Role Not Found"));
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        responseDto.setMessage("Registrasi Berhasil");
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MessageResponseDto responseDto = MessageResponseDto.builder().message("Login Berhasil").build();
        return ResponseEntity.ok(responseDto);
    }

}
