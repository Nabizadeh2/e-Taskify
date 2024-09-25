package com.example.etaskify.service;

import com.example.etaskify.dto.request.OrganizationRequest;
import com.example.etaskify.dto.request.TaskRequest;
import com.example.etaskify.dto.response.ExceptionResponse;
import com.example.etaskify.dto.response.MessageResponse;
import com.example.etaskify.dto.response.TaskResponse;
import com.example.etaskify.entity.OrganizationEntity;
import com.example.etaskify.exception.NotFoundException;
import com.example.etaskify.mapper.OrganizationMapper;
import com.example.etaskify.mapper.TaskMapper;
import com.example.etaskify.mapper.UserMapper;
import com.example.etaskify.repository.TaskRepository;
import com.example.etaskify.repository.UserRepository;
import com.example.etaskify.service.impl.OrganizationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.aspectj.bridge.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.beans.Transient;

@RequiredArgsConstructor
@Service
public class OrganizationService implements OrganizationServiceImpl {

    private final OrganizationMapper organizationMapper;
    private final UserMapper userMapper;
   // private final RoleRepository roleRepository;
    private final MessageUtil messageUtil;
    private final UserRepository userRepository;
   //private final TokenService tokenService;
   // private final EmailUtil emailUtil;
   // private final EmailService emailService;
    private final UserService userService;
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public ResponseEntity<MessageResponse> create(OrganizationRequest request) {

        return null;
    }


    @Override
    public ResponseEntity<MessageResponse> createTask(TaskRequest request) {

        return null;
    }


    @Override
    public ResponseEntity<Page<TaskResponse>> getAllAvailableTask(int pageNumber, int pageSize) {

        return null;
    }


    @Override
    public OrganizationEntity getById(long id) {
        return null;
    }
}
