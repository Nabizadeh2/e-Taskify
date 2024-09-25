package com.example.etaskify.mapper;

import com.example.etaskify.dto.request.TaskRequest;
import com.example.etaskify.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import javax.swing.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
           unmappedSourcePolicy = ReportingPolicy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    TaskRequest toDto(TaskEntity taskEntity);
    TaskEntity toEntity(TaskRequest taskRequest);

}
