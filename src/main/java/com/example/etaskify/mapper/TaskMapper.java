package com.example.etaskify.mapper;

import com.example.etaskify.dto.request.TaskRequest;
import com.example.etaskify.dto.response.TaskResponse;
import com.example.etaskify.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.scheduling.config.Task;

import javax.swing.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
           unmappedSourcePolicy = ReportingPolicy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    @Mapping(target = "users",ignore = true)
    TaskEntity map(TaskRequest request, @MappingTarget TaskEntity taskEntity);
    TaskResponse map(TaskEntity taskEntity);


    TaskRequest toDto(TaskEntity taskEntity);
    TaskEntity toEntity(TaskRequest taskRequest);

}
