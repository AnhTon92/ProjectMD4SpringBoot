package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.constants.RoleName;
import com.ra.projectspringboot.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
