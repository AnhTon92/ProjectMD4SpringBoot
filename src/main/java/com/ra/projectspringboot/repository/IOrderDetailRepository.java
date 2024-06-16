package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrderId(Long orderId);

    boolean existsByOrderId(Long orderId);

    List<OrderDetail> findAllByOrderSerialNumber(String serialNumber);
}
