package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.Order;
import com.ra.projectspringboot.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByStatus(OrderStatus orderStatus);
}
