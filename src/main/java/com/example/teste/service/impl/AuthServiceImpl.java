package com.example.teste.service.impl;

import com.example.teste.config.JwtService;
import com.example.teste.dto.AuthenticationRequest;
import com.example.teste.dto.AuthenticationResponse;
import com.example.teste.dto.RegisterRequest;
import com.example.teste.model.AuthUser;
import com.example.teste.model.Role;
import com.example.teste.model.User;
import com.example.teste.repository.AuthRepository;
import com.example.teste.repository.UserRepository;
import com.example.teste.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthRepository authrepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var authUser = AuthUser
                .builder().
                 firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        authrepository.save(authUser);
        var jwtToken = jwtService.generateToken(authUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = authrepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
