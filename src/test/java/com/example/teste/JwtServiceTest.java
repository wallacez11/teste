package com.example.teste;

import com.example.teste.config.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private final JwtService jwtService = new JwtService();
    private static final String SECRET_KEY = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
    @Test
    public void testGenerateAndValidateToken() {
        // Given
        User userDetails = new User("testuser", "password", new ArrayList<>());
        String token = jwtService.generateToken(userDetails);

        // When
        boolean isTokenValid = jwtService.isTokenValid(token, userDetails);

        // Then
        assertTrue(isTokenValid);
    }

    @Test
    public void testGenerateTokenWithExtraClaims() {
        // Given
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("customClaim", "customValue");
        User userDetails = new User("testuser", "password", new ArrayList<>());

        // When
        String token = jwtService.generateToken(extraClaims, userDetails);

        // Then
        assertNotNull(token);
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    public void testExtractUsernameFromToken() {
        // Given
        User userDetails = new User("testuser", "password", new ArrayList<>());
        String token = jwtService.generateToken(userDetails);

        // When
        String extractedUsername = jwtService.extractUsername(token);

        // Then
        assertEquals("testuser", extractedUsername);
    }

}