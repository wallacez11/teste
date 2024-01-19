package com.example.teste.service;

import com.example.teste.dto.AuthenticationRequest;
import com.example.teste.dto.AuthenticationResponse;
import com.example.teste.dto.RegisterRequest;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
