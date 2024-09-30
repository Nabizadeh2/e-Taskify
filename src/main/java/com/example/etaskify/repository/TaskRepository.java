package com.example.etaskify.repository;

import com.example.etaskify.entity.OrganizationEntity;
import com.example.etaskify.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

import java.awt.print.Pageable;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    Page<TaskEntity> findAllByOrganization(OrganizationEntity organizationEntity, Pageable pageable);
}
