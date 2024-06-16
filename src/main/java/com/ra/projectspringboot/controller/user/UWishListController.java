package com.ra.projectspringboot.controller.user;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IWistlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/wishlist")
@RequiredArgsConstructor
public class UWishListController {
    private final IWistlistService wistlistService;

    /**
     * lấy ra danh sách sản phẩm yêu thích
     */
    @GetMapping
    public ResponseEntity<?> viewAllWishlist() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(wistlistService.getAllWishlist())
                .build());
    }

    /**
     * @param productId Long
     *                  thêm sản phẩm yêu thích vào danh sách
     */
    @PostMapping("/{productId}/like")
    public ResponseEntity<?> likeProduct(@PathVariable Long productId) throws CustomException {
        wistlistService.addProductToWishist(productId);
        return new ResponseEntity<>(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(201)
                .data("like product successfully")
                .build(), HttpStatus.CREATED);
    }

    /**
     * @param wishlistId Long
     *                   xóa sản phẩm trong danh sách yêu thích
     * */
    @DeleteMapping("/{wishlistId}/delete")
    public ResponseEntity<?> deleteWishListById(@PathVariable Long wishlistId) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data("delete wishlist successfully")
                .build());
    }

}
