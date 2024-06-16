package com.ra.projectspringboot.service;

import com.ra.projectspringboot.dto.response.OrderDetailResponse;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Order;

import java.util.List;

public interface IOrderService {

    Order changeStatusOrder(String newStatus,Long orderId) throws CustomException;

    List<OrderDetailResponse> showDetailByOrderId(Long orderId) throws CustomException;

    List<Order> getAllOrderByStatus(String status) throws CustomException;

    List<Order> getAllOrder();
}
