package com.example.etaskify.repository;

import com.example.etaskify.entity.OrganizationEntity;
import com.example.etaskify.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("select _ from _user _ where _.username = ?1 and _.enabled = true ")
    Optional<UserEntity> findByUsernameAndEnabled(String username);

    List<UserEntity> findByOrganization(OrganizationEntity organization);

    List<UserEntity> findByOrganizationAndEnabled(OrganizationEntity organization, boolean enabled);
}
