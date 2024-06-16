package com.ra.projectspringboot.controller.admin;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.dto.request.CategoryRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/categories")
@RequiredArgsConstructor
public class ACategoryController {
    private final ICategoryService categoryService;

    /**
     * @param pageable Pageable
     *                 lấy ra danh sách danh mục ( phần trang sắp xếp )
     */
    @GetMapping
    public ResponseEntity<?> findAllCategory(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(categoryService.findAllCategory(pageable))
                .build());
    }

    /**
     * @param categoryId Long
     *                   tìm danh mục theo id
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(categoryService.findCategoryById(categoryId))
                .build());
    }

    /**
     * @param categoryRequest CategoryRequest
     *                        thêm mới danh mục
     */
    @PostMapping
    public ResponseEntity<?> addNewCategory(@Valid @RequestBody CategoryRequest categoryRequest) throws CustomException {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(201)
                .data(categoryService.addNewCategory(categoryRequest))
                .build(), HttpStatus.CREATED);
    }

    /**
     * @param categoryId      Long
     * @param categoryRequest CategoryRequest
     *                        cập nhật thông tin danh mục
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(categoryService.updateCategory(categoryRequest, categoryId))
                .build());
    }

    /**
     * @param categoryId Long
     *                   xóa danh mục theo id
     */
    @DeleteMapping("/{categoryId}/delete")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) throws CustomException {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data("delete successfully")
                .build());
    }

}
