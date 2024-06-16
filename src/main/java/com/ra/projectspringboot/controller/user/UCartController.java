package com.ra.projectspringboot.controller.user;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.dto.request.CartItemRequest;
import com.ra.projectspringboot.dto.request.CheckoutRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.ICartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UCartController {
    private final ICartService cartService;

    /**
     * lấy ra giỏ hàng người dùng
     */
    @GetMapping
    public ResponseEntity<?> getAllCartUser() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(cartService.findAllCartItem())
                .build());
    }

    /**
     * @param cartItemRequest CartItemRequest
     *                        thêm sản phẩm vào giỏ hàng
     */
    @PostMapping
    public ResponseEntity<?> addNewCartItem(@Valid @RequestBody CartItemRequest cartItemRequest) throws CustomException {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(201)
                .data(cartService.addNewCartItem(cartItemRequest))
                .build(),
                HttpStatus.CREATED);
    }

    /**
     * @param cartItemId Long
     * @param quantity   Integer
     *                   thay đổi số lượng cartItem
     */
    @PutMapping("/{cartItemId}/changeQuantity")
    public ResponseEntity<?> changeQuantityCartItem(@PathVariable Long cartItemId, @RequestParam Integer quantity) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(cartService.changeQuantity(cartItemId, quantity))
                .build());
    }

    /**
     * @param cartItemId Long
     *                   xóa cartItem thông qua id
     */
    @DeleteMapping("/{cartItemId}/delete")
    public ResponseEntity<?> deleteCartItemById(@PathVariable Long cartItemId) throws CustomException {
        cartService.deleteCartItemById(cartItemId);
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data("delete cartItem successfully")
                .build());
    }

    /**
     * xóa toàn bộ cartItem thông qua userId
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllCartItem() {
        cartService.deleteAllCartItem();
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data("delete all cartItem successfully")
                .build());
    }

    /**
     * @param checkoutRequest CheckoutRequest
     *                        đặt hàng ( purchase )
     * */
    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutCartUser(@Valid @RequestBody CheckoutRequest checkoutRequest) throws CustomException {
        cartService.checkout(checkoutRequest);
        return new ResponseEntity<>("checkout successfully", HttpStatus.CREATED);
    }

}
