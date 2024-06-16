package com.ra.projectspringboot.controller.user;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/orders")
@RequiredArgsConstructor
public class UOrderController {
    private final IOrderService orderService;

    /**
     * @param orderId Long
     *                hủy đơn hàng ở phía người dùng
     */
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrderUser(@PathVariable Long orderId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(orderService.cancelOrderUser(orderId))
                .build());
    }

    /**
     * @param status String
     *               lấy danh sách lịch sử theo trạng thái đơn hàng
     */
    @GetMapping("/orderStatus")
    public ResponseEntity<?> getAllOrderByStatus(@RequestParam String status) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(orderService.getAllOrderUserByStatus(status))
                .build());
    }

    /**
     * @param serialNumber String
     *                     lấy ra danh sách đơn hàng chi tiết theo serialNumber
     */
    @GetMapping("/detail/serialNumber")
    public ResponseEntity<?> getDetailBySerialNumber(@RequestParam String serialNumber) {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(orderService.getDetailBySerialNumberOrder(serialNumber))
                .build());
    }

    /**
     * lấy ra danh sách lịch sử mua hàng
     */
    @GetMapping
    public ResponseEntity<?> getAllOrderUser() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(orderService.getAllOrderUser())
                .build());
    }

}
