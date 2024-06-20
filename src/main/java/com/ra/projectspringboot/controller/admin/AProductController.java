package com.ra.projectspringboot.controller.admin;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.dto.request.ProductRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AProductController {
    private final IProductService productService;

    /**
     * @param pageable Pageable
     *                 lấy ra danh sách sản phẩm
     */
    @GetMapping
    public ResponseEntity<?> findAllProduct(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.findAllProduct(pageable))
                .build());
    }

    /**
     * @param productId Long
     *                  lấy ra sản phẩm theo id
     */
    @GetMapping("/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable Long productId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.findProductById(productId))
                .build());
    }

    /**
     * @param productRequest ProductRequest
     *                       thêm mới sản phẩm
     */
    @PostMapping
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody ProductRequest productRequest) throws CustomException {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(201)
                .data(productService.addNewProduct(productRequest))
                .build(),
                HttpStatus.CREATED);
    }

    /**
     * @param productId      Long
     * @param productRequest ProductRequest
     *                       cập nhật thông tin product
     */
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long productId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(productService.updateProduct(productRequest, productId))
                .build());
    }

    /**
     * @param productId Long
     *                  xóa sản phẩm
     */
    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<?> deleteProductId(@PathVariable Long productId) throws CustomException {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data("delete successfully")
                .build());
    }

}
