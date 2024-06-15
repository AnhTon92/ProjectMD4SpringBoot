package com.ra.projectspringboot.controller.admin;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @GetMapping
    public ResponseEntity<?> findAllRole() {
        return ResponseEntity.ok().body(
                ResponseWrapper.builder()
                        .status(EHttpStatus.SUCCESS)
                        .code(200)
                        .data(roleService.findAll())
                        .build()
        );
    }

}
