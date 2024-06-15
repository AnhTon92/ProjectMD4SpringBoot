package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.dto.response.UserResponse;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.User;
import com.ra.projectspringboot.repository.IUserRepository;
import com.ra.projectspringboot.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

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
        user.setStatus(!user.getStatus());
        return toUserResponse(userRepository.save(user));
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .user_id(user.getUser_id())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .status(user.getStatus())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .address(user.getAddress())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .is_deleted(user.getIs_deleted())
                .build();
    }
}
