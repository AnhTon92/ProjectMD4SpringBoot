package com.ra.projectspringboot.controller.all;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/product")
@RequiredArgsConstructor
public class AllProductController {
    private final IProductService productService;

    /**
     * @param productId Long
     *                  lấy ra sản phẩm theo id
     */
    @GetMapping("/{productId}")
    public ResponseEntity<?> viewProductById(@PathVariable Long productId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.findById(productId))
                .build());
    }

    /**
     * @param categoryId Long
     *                   lấy sản phẩm theo danh mục
     */
    @GetMapping("/{categoryId}/category")
    public ResponseEntity<?> viewProductByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.findAllByCategoryId(categoryId))
                .build());
    }

    /**
     * Danh sách sản phâẩm mới nhất
     */
    @GetMapping("/new")
    public ResponseEntity<?> newListProduct() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.newListProduct())
                .build());
    }

    /**
     * Danh sách sản phẩm bán chạy
     */
    @GetMapping("/bestSeller")
    public ResponseEntity<?> bestSellerProduct() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.bestSellerProduct())
                .build());
    }

    /**
     * danh sách sản phẩm nổi bật
     */
    @GetMapping("/popular")
    public ResponseEntity<?> popularProduct() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.listOfFeaturedProducts())
                .build());
    }

    /**
     * @param pageable Pageable
     *                 danh sách sản phẩm được bán (phân trang sắp xếp)
     */
    @GetMapping("/statusIsTrue")
    public ResponseEntity<?> viewProductNew(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.findAllIsDeleteIsFalse(pageable))
                .build());
    }

    /**
     * @param search String
     *               Tìm kiếm sản phẩm theo tên hoặc mô tả
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchByNameOrDescription(@RequestParam String search) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.searchByNameOrDescription(search))
                .build());
    }

}
