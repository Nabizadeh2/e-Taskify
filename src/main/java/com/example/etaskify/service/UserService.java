package com.example.etaskify.service;

import com.example.etaskify.dto.request.PasswordRequest;
import com.example.etaskify.dto.request.UserRequest;
import com.example.etaskify.dto.response.MessageResponse;
import com.example.etaskify.dto.response.UserResponse;
import com.example.etaskify.service.impl.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserService implements UserServiceImpl {
    @Override
    public ResponseEntity<MessageResponse> create(UserRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<MessageResponse> confirm(PasswordRequest request, String token) {
        return null;
    }

    @Override
    public List<User> findAllById(List<Long> ids) {
        return null;
    }

    @Override
    public long getOrgId() {
        return 0;
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllOrganizationUser() {
        return null;
    }
}
