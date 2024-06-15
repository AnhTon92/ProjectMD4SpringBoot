package com.ra.projectspringboot.service;

import com.ra.projectspringboot.constants.RoleName;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    Role findByRoleName(RoleName roleName) throws CustomException;
}
