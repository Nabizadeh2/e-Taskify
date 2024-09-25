package com.example.etaskify.mapper;

import com.example.etaskify.dto.request.UserRequest;
import com.example.etaskify.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
  unmappedTargetPolicy =ReportingPolicy.IGNORE )
public interface  UserMapper {

    UserRequest toDto (UserEntity userEntity);
    UserEntity toEntity(UserRequest userRequest);
}
