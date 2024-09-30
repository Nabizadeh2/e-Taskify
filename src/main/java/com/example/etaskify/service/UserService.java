package com.example.etaskify.service;

import com.example.etaskify.config.AuthenticationDetails;
import com.example.etaskify.dto.request.PasswordRequest;
import com.example.etaskify.dto.request.UserRequest;
import com.example.etaskify.dto.response.ExceptionResponse;
import com.example.etaskify.dto.response.MessageResponse;
import com.example.etaskify.dto.response.UserResponse;
import com.example.etaskify.entity.OrganizationEntity;
import com.example.etaskify.entity.Role;
import com.example.etaskify.entity.TokenEntity;
import com.example.etaskify.entity.UserEntity;
import com.example.etaskify.enums.TokenType;
import com.example.etaskify.exception.NotFoundException;
import com.example.etaskify.mapper.UserMapper;
import com.example.etaskify.repository.OrganizationRepository;
import com.example.etaskify.repository.RoleRepository;
import com.example.etaskify.repository.TokenRepository;
import com.example.etaskify.repository.UserRepository;
import com.example.etaskify.service.impl.UserServiceImpl;
import com.example.etaskify.util.EmailUtil;
import com.example.etaskify.util.MessageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl {
    private final MessageUtil messageUtil;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final EmailUtil emailUtil;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public ResponseEntity<MessageResponse> create(UserRequest userRequest) {
        OrganizationEntity organizationEntity = organizationRepository.findById(getOrgId()).orElseThrow(()->
                NotFoundException.of(ExceptionResponse.of("NOT_SAME_PASSWORD", HttpStatus.NOT_FOUND),getOrgId()));

        Role roleUser = roleRepository.findByName("ROLE_USER").orElseThrow(() ->
                NotFoundException.of(ExceptionResponse.of("NOT_FOUND", HttpStatus.NOT_FOUND), "ROLE_USER"));

        UserEntity userEntity = userMapper.map(userRequest, new UserEntity(organizationEntity, roleUser));

        userRepository.save(userEntity);

        String confirmationToken = tokenService.generateAndSaveConfirmationToken(userEntity);

        String subject = emailUtil.confirmEmailSubjectBuilder(confirmationToken,userEntity.getUsername());

        emailService.sendTo(userEntity.getEmail(),subject);

        return ResponseEntity.ok(MessageResponse.of(messageUtil.getMessage("MESSAGE_SUCCESSFULLY","ROLE_USER" ),HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public ResponseEntity<MessageResponse> confirm(PasswordRequest request, String token) {
        TokenEntity foundedToken = tokenRepository.findByTokenTypeAndNameAndAvailable(TokenType.CONFIRMATION, token, true).orElseThrow(() -> NotFoundException.of(ExceptionResponse.of(
                "NOT_FOUND",HttpStatus.NOT_FOUND), token));
        foundedToken.isUsableOrElseThrow();

        UserEntity userEntity = foundedToken.getUserEntity();
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setEnabled(true);
        foundedToken.unusable();
        return ResponseEntity.ok(MessageResponse.of(messageUtil.getMessage("MESSAGE_SUCCESSFULLY","ROLE_USER"), HttpStatus.NOT_FOUND));
    }

    public List<UserEntity> findAllById(List<Long> id){
        return userRepository.findAllById(id);
    }
    public long getOrgId(){
        return ((AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getOrganizationId();
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllOrganizationUser() {
        OrganizationEntity organizationEntity = organizationRepository.findById(getOrgId()).orElseThrow(() -> NotFoundException.of(ExceptionResponse.of("NOT_FOUND", HttpStatus.NOT_FOUND),"ROLE_USER"));
        List<UserEntity> allUsers = userRepository.findByOrganizationAndEnabled(organizationEntity, true);
        List<UserResponse> userResponses = allUsers.stream()
                .map(userMapper::map)
                .toList();
        return ResponseEntity.ok(userResponses);
    }
}
