package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
