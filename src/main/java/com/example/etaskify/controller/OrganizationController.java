package com.example.etaskify.controller;

import com.example.etaskify.dto.request.OrganizationRequest;
import com.example.etaskify.dto.request.TaskRequest;
import com.example.etaskify.dto.response.MessageResponse;
import com.example.etaskify.dto.response.TaskResponse;
import com.example.etaskify.service.OrganizationService;
import com.example.etaskify.service.impl.OrganizationServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/organization")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class OrganizationController {

    private final OrganizationServiceImpl organizationServiceImpl;

    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody @Valid OrganizationRequest request) {
        return organizationServiceImpl.create(request);
    }

    @PostMapping("/task")
    public ResponseEntity<MessageResponse> createTask(@RequestBody @Valid TaskRequest request){
        return organizationServiceImpl.createTask(request);
    }

    @GetMapping("/task")
    public ResponseEntity<Page<TaskResponse>> getAllAvailableTask(@RequestParam(required = false,defaultValue = "0") int pageNumber,
                                                                  @RequestParam(required = false,defaultValue = "10") int pageSize){
        return organizationServiceImpl.getAllAvailableTask(pageNumber,pageSize);
    }

}
