package com.ra.projectspringboot.controller.all;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/categories")
@RequiredArgsConstructor
public class AllCategoryController {
    private final ICategoryService categoryService;

    /**
     * lấy da ra danh mục được bán (status is true)
     * */
    @GetMapping("/statusIsTrue")
    public ResponseEntity<?> viewCategoryStatusIsTrue() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(categoryService.findAllCategoryStatusIsTrue())
                .build());
    }



}
