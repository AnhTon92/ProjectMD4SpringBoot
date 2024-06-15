package com.ra.projectspringboot.service;

import com.ra.projectspringboot.constants.RoleName;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Role;

public interface IRoleService {
    Role findByRoleName(RoleName roleName) throws CustomException;
}
