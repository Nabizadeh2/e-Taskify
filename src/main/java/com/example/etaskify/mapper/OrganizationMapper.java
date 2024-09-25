package com.example.etaskify.mapper;

import com.example.etaskify.dto.request.OrganizationRequest;
import com.example.etaskify.entity.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizationMapper {
    OrganizationRequest toDto (OrganizationEntity organizationEntity);
    OrganizationEntity toEntity (OrganizationRequest organizationRequest);

}
