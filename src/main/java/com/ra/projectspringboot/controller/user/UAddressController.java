package com.ra.projectspringboot.controller.user;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.dto.request.AddressRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/address")
@RequiredArgsConstructor
public class UAddressController {
    private final IAddressService addressService;

    /**
     * lấy ra danh sách địa chỉ theo user đang đăng nhập
     */
    @GetMapping
    public ResponseEntity<?> getAllAddressUser() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(addressService.getAllAddress())
                .build());
    }

    /**
     * @param addressId Long
     *                  lấy ra địa chỉ theo id
     */
    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddressById(@PathVariable Long addressId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(addressService.getAddressById(addressId))
                .build());
    }

    /**
     * @param addressRequest AddressRequest
     *                       thêm mới địa chỉ người dùng
     */
    @PostMapping
    public ResponseEntity<?> addNewAddress(@Valid @RequestBody AddressRequest addressRequest) throws CustomException {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(201)
                .data(addressService.addNewAddress(addressRequest))
                .build(),
                HttpStatus.CREATED);
    }

    /**
     * @param addressId Long
     *                  xóa address theo id
     */
    @DeleteMapping("/{addressId}/delete")
    public ResponseEntity<?> deleteAddressById(@PathVariable Long addressId) throws CustomException {
        addressService.deleteAddressById(addressId);
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data("delete address successfully")
                .build());
    }

}
