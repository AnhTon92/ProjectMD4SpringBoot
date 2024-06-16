package com.ra.projectspringboot.controller.admin;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class AUserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(@PageableDefault(page = 0, size = 5, sort = "user_id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(userService.findAll(pageable))
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUsersByFullName(@RequestParam String search) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(userService.searchByFullName(search))
                .build());
    }

    @PutMapping("/{userId}/status")
    public ResponseEntity<?> changeStatus(@PathVariable Long userId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(userService.changeStatusUser(userId))
                .build());
    }

}
