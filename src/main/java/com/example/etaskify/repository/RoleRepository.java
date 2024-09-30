package com.example.etaskify.repository;

import com.example.etaskify.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findRoleByName(String roleUser);

    Optional<Role> findByName(String name);
}
