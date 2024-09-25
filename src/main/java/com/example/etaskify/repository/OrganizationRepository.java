package com.example.etaskify.repository;

import com.example.etaskify.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OrganizationRepository extends JpaRepository<OrganizationEntity,Long> {
}
