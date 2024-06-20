package com.ra.projectspringboot.controller.user;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.dto.request.ChangePasswordRequest;
import com.ra.projectspringboot.dto.request.UpdateUserRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/account")
@RequiredArgsConstructor
public class UAccountController {
    private final IUserService userService;

    /**
     * lấy thông tin người dùng đang đăng nhập
     */
    @GetMapping
    public ResponseEntity<?> viewInformation() throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(userService.getInformation())
                .build());
    }

    /**
     * cập nhật thông tin người dùng
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateInformation(@Valid @RequestBody UpdateUserRequest updateUserRequest) throws CustomException {
        userService.updateInformation(updateUserRequest);
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data("update account successfully")
                .build());
    }

    /**
     * thay đổi mật khẩu
     */
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) throws CustomException {
        userService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data("update password successfully")
                .build());
    }

}
