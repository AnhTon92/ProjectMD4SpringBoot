package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.constants.RoleName;
import com.ra.projectspringboot.dto.request.ChangePasswordRequest;
import com.ra.projectspringboot.dto.request.UpdateUserRequest;
import com.ra.projectspringboot.dto.response.UserResponse;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.User;
import com.ra.projectspringboot.repository.IUserRepository;
import com.ra.projectspringboot.security.principal.UserDetailCustom;
import com.ra.projectspringboot.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::toUserResponse);
    }

    @Override
    public List<UserResponse> searchByFullName(String fullName) {
        return userRepository.findAllByFullNameContains(fullName).stream().map(this::toUserResponse).toList();
    }

    @Override
    public UserResponse changeStatusUser(Long userId) throws CustomException {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("user not found", HttpStatus.NOT_FOUND));
        if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN))) {
            throw new CustomException("admin is not blocked", HttpStatus.BAD_REQUEST);
        }
        user.setStatus(!user.getStatus());
        return toUserResponse(userRepository.save(user));
    }

    @Override
    public UserDetailCustom getCurrentUser() {
        return (UserDetailCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User findUserById(Long userId) throws CustomException {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException("user not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserResponse getInformation() throws CustomException {
        User user = userRepository.findById(getCurrentUser().getId()).orElseThrow(() -> new CustomException("user not found", HttpStatus.NOT_FOUND));
        return toUserResponse(user);
    }

    @Override
    public void updateInformation(UpdateUserRequest updateUserRequest) throws CustomException {
        User user = userRepository.findById(getCurrentUser().getId()).orElseThrow(() -> new CustomException("user not found", HttpStatus.NOT_FOUND));
        user.setFullName(updateUserRequest.getFullName());
        user.setPhone(updateUserRequest.getPhone());
        user.setAddress(updateUserRequest.getAddress());
        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) throws CustomException {
        User user = userRepository.findById(getCurrentUser().getId()).orElseThrow(() -> new CustomException("user not found", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            if (changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                userRepository.save(user);
            } else {
                throw new CustomException("password not the same", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new CustomException("old password is wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .status(user.getStatus())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }
}
