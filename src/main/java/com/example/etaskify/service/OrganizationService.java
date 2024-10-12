package com.example.etaskify.service;

import com.example.etaskify.dto.request.OrganizationRequest;
import com.example.etaskify.dto.request.TaskRequest;
import com.example.etaskify.dto.response.ExceptionResponse;
import com.example.etaskify.dto.response.MessageResponse;
import com.example.etaskify.dto.response.TaskResponse;
import com.example.etaskify.entity.OrganizationEntity;
import com.example.etaskify.entity.Role;
import com.example.etaskify.entity.TaskEntity;
import com.example.etaskify.entity.UserEntity;
import com.example.etaskify.exception.NotFoundException;
import com.example.etaskify.mapper.OrganizationMapper;
import com.example.etaskify.mapper.TaskMapper;
import com.example.etaskify.mapper.UserMapper;
import com.example.etaskify.repository.OrganizationRepository;
import com.example.etaskify.repository.RoleRepository;
import com.example.etaskify.repository.TaskRepository;
import com.example.etaskify.repository.UserRepository;
import com.example.etaskify.service.impl.OrganizationServiceImpl;
import com.example.etaskify.util.EmailUtil;
import com.example.etaskify.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.beans.Transient;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrganizationService implements OrganizationServiceImpl {
    private final OrganizationMapper organizationMapper;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final MessageUtil messageUtil;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final EmailUtil emailUtil;
    private final EmailService emailService;
    private final UserService userService;
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public ResponseEntity<MessageResponse> create(OrganizationRequest request) {
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN").orElseThrow(()-> NotFoundException.of(ExceptionResponse.of("NOT_FOUND",HttpStatus.NOT_FOUND),"ROLE_ADMIN"));

        OrganizationEntity organizationEntity = organizationMapper.map(request, new OrganizationEntity());
        OrganizationEntity savedOrganization = organizationRepository.save(organizationEntity);

        UserEntity userEntity = userMapper.map(request.getUser(), new UserEntity(savedOrganization,roleAdmin));

        userRepository.save(userEntity);

        String confirmationToken = tokenService.generateAndSaveConfirmationToken(userEntity);

        String subject = emailUtil.confirmEmailSubjectBuilder(confirmationToken, userEntity.getUsername());

        emailService.sendTo(userEntity.getEmail(),subject);

        return ResponseEntity.ok(MessageResponse.of(messageUtil.getMessage("MESSAGE_SEND_TO_EMAIL","ROLE_ADMIN"),HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<MessageResponse> createTask(TaskRequest request) {
        List<UserEntity> users = userService.findAllById(request.getUsers());
        OrganizationEntity organizationEntity = getById(userService.getOrgId());
        TaskEntity task = taskMapper.map(request, new TaskEntity(users,organizationEntity));

        String subject = emailUtil.taskMessageSubjectBuilder(task);
        taskRepository.save(task);

        users.forEach(user -> emailService.sendTo(user.getEmail(),subject));

        return ResponseEntity.ok(MessageResponse.of(messageUtil.getMessage("MESSAGE_SUCCESSFULLY", "ROLE_ADMIN"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Page<TaskResponse>> getAllAvailableTask(int pageNumber,int pageSize) {
        Page<TaskEntity> tasks = taskRepository.findAllByOrganization(getById(userService.getOrgId()), (Pageable) PageRequest.of(12 ,33));

        Page<TaskResponse> taskResponses = tasks.map(taskMapper::map);

        return ResponseEntity.ok(taskResponses);
    }

    public OrganizationEntity getById(long id){
        return organizationRepository.findById(id).orElseThrow(()->NotFoundException.of(ExceptionResponse.of("NOT_FOUND", HttpStatus.NOT_FOUND),"ROLE_ADMIN"));
    }
}
