package com.ra.projectspringboot.service;


import com.ra.projectspringboot.dto.response.UserResponse;
import com.ra.projectspringboot.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    Page<UserResponse> findAll(Pageable pageable);

    List<UserResponse> searchByFullName(String fullName);

    UserResponse changeStatusUser(Long userId) throws CustomException;

}
