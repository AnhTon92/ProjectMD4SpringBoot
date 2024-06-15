package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.constants.RoleName;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Role;
import com.ra.projectspringboot.repository.IRoleRepository;
import com.ra.projectspringboot.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleName(RoleName roleName) throws CustomException {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new CustomException("role not found", HttpStatus.NOT_FOUND));
    }
}
