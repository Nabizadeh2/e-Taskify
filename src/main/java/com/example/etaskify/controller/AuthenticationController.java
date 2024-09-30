package com.example.etaskify.controller;

import com.example.etaskify.dto.request.LoginRequest;
import com.example.etaskify.dto.response.TokenResponse;
import com.example.etaskify.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ap/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
        return authenticationService.login(request);
    }

    @GetMapping
    public ResponseEntity<TokenResponse> refreshToken(@RequestParam String token){
        return authenticationService.tokenByRefreshToken(token);
    }
}
