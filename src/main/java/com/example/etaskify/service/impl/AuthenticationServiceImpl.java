package com.example.etaskify.service.impl;

import com.example.etaskify.dto.request.LoginRequest;
import com.example.etaskify.dto.response.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationServiceImpl {
    ResponseEntity<TokenResponse> login(LoginRequest request);
    ResponseEntity<TokenResponse> tokenByRefreshToken(String token);
}
