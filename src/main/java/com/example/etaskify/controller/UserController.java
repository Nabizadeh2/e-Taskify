package com.example.etaskify.controller;

import com.example.etaskify.dto.request.PasswordRequest;
import com.example.etaskify.dto.request.UserRequest;
import com.example.etaskify.dto.response.MessageResponse;
import com.example.etaskify.dto.response.UserResponse;
import com.example.etaskify.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody @Valid UserRequest request){
        return userServiceImpl.create(request);
    }
    @PatchMapping
    public ResponseEntity<MessageResponse> confirm(@RequestParam String token,
                                                   @RequestBody @Valid PasswordRequest request){
        return userServiceImpl.confirm(request,token);
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllOrgUser(){
        return userServiceImpl.getAllOrganizationUser();
    }


}
