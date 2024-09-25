package com.example.etaskify.service.impl;

import com.example.etaskify.dto.request.OrganizationRequest;
import com.example.etaskify.dto.request.TaskRequest;
import com.example.etaskify.dto.response.MessageResponse;
import com.example.etaskify.dto.response.TaskResponse;
import com.example.etaskify.entity.OrganizationEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface OrganizationServiceImpl {
    ResponseEntity<MessageResponse> create(OrganizationRequest request);

    ResponseEntity<MessageResponse> createTask(TaskRequest request);

    ResponseEntity<Page<TaskResponse>> getAllAvailableTask(int pageNumber, int pageSize);
    OrganizationEntity getById(long id);
}
