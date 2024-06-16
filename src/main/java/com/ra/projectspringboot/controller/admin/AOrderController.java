package com.ra.projectspringboot.controller.admin;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/orders")
@RequiredArgsConstructor
public class AOrderController {
    private final IOrderService orderService;

    /**
     * @param newStatus String
     * @param orderId   Long
     *                  thay đổi trạng thái đơn hàng
     */
    @PutMapping("/{orderId}/changeStatus")
    public ResponseEntity<?> changeStatusOrder(@RequestParam String newStatus, @PathVariable Long orderId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(orderService.changeStatusOrder(newStatus, orderId))
                .build());
    }

    /**
     * @param orderId Long
     *                lấy ra đơn hàng chi tiết dựa vào orderId ( orderDetail )
     */
    @GetMapping("/{orderId}/detail")
    public ResponseEntity<?> getAllOrderDetail(@PathVariable Long orderId) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(orderService.showDetailByOrderId(orderId))
                .build());
    }

    /**
     * @param status String
     *               lấy ra danh sách đơn hàng theo trạng thái đơn hàng ( orderStatus )
     */
    @GetMapping("/searchByStatus")
    public ResponseEntity<?> getAllOrderByStatus(@RequestParam String status) throws CustomException {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(orderService.getAllOrderByStatus(status))
                .build());
    }

    /**
     * lấy ra danh sách tất cả đơn hàng
     * */
    @GetMapping
    public ResponseEntity<?> getAllOrder() {
        return ResponseEntity.ok().body(ResponseWrapper.builder()
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .data(orderService.getAllOrder())
                .build());
    }

}
