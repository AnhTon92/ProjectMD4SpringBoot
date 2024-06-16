package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.dto.response.OrderDetailResponse;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Order;
import com.ra.projectspringboot.model.entity.OrderDetail;
import com.ra.projectspringboot.model.entity.OrderStatus;
import com.ra.projectspringboot.repository.IOrderDetailRepository;
import com.ra.projectspringboot.repository.IOrderRepository;
import com.ra.projectspringboot.service.IOrderService;
import com.ra.projectspringboot.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IUserService userService;

    @Override
    public Order changeStatusOrder(String newStatus, Long orderId) throws CustomException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new CustomException("order not found", HttpStatus.NOT_FOUND));
        try {
            order.setStatus(OrderStatus.valueOf(newStatus));
        } catch (Exception e) {
            throw new CustomException("your send newStatus wrong", HttpStatus.BAD_REQUEST);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<OrderDetailResponse> showDetailByOrderId(Long orderId) throws CustomException {
        if (orderDetailRepository.existsByOrderId(orderId)) {
            return orderDetailRepository.findAllByOrderId(orderId).stream().map(this::toOrderDetailResponse).toList();
        }
        throw new CustomException("order not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Order> getAllOrderByStatus(String status) throws CustomException {
        try {
            return orderRepository.findAllByStatus(OrderStatus.valueOf(status));
        } catch (Exception e) {
            throw new CustomException(status + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order cancelOrderUser(Long orderId) throws CustomException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new CustomException("order not found", HttpStatus.NOT_FOUND));
        order.setStatus(OrderStatus.CANCEL);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrderUserByStatus(String status) throws CustomException {
        try {
            return orderRepository.findAllByStatusAndUserId(OrderStatus.valueOf(status), userService.getCurrentUser().getId());
        } catch (Exception e) {
            throw new CustomException(status + " can't not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<OrderDetailResponse> getDetailBySerialNumberOrder(String serialNumber) {
        return orderDetailRepository.findAllByOrderSerialNumber(serialNumber).stream().map(this::toOrderDetailResponse).toList();
    }

    @Override
    public List<Order> getAllOrderUser() {
        return orderRepository.findAllByUserId(userService.getCurrentUser().getId());
    }

    public OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .product(orderDetail.getProduct())
                .name(orderDetail.getName())
                .unitPrice(orderDetail.getUnitPrice())
                .orderQuantity(orderDetail.getOrderQuantity())
                .build();
    }

}
