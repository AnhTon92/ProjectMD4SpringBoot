package com.ra.projectspringboot.service;


import com.ra.projectspringboot.dto.request.ChangePasswordRequest;
import com.ra.projectspringboot.dto.request.UpdateUserRequest;
import com.ra.projectspringboot.dto.response.JwtResponse;
import com.ra.projectspringboot.dto.response.UserResponse;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.User;
import com.ra.projectspringboot.security.principal.UserDetailCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    Page<UserResponse> findAll(Pageable pageable);

    List<UserResponse> searchByFullName(String fullName);

    UserResponse changeStatusUser(Long userId) throws CustomException;

    UserDetailCustom getCurrentUser();

    User findUserById(Long userId) throws CustomException;

    UserResponse getInformation() throws CustomException;

    void updateInformation(UpdateUserRequest updateUserRequest) throws CustomException;

    void changePassword(ChangePasswordRequest changePasswordRequest) throws CustomException;

}
