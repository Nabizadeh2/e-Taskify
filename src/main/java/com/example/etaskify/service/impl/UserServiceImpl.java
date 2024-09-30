package com.example.etaskify.service.impl;

import com.example.etaskify.dto.request.PasswordRequest;
import com.example.etaskify.dto.request.UserRequest;
import com.example.etaskify.dto.response.MessageResponse;
import com.example.etaskify.dto.response.UserResponse;
import com.example.etaskify.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceImpl {
    ResponseEntity<MessageResponse> create(UserRequest request);

    ResponseEntity<MessageResponse> confirm(PasswordRequest request, String token);
    List<UserEntity> findAllById(List<Long> id);
    long getOrgId();

    ResponseEntity<List<UserResponse>> getAllOrganizationUser();
}
