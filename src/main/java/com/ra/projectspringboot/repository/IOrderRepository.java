package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order,Long> {
}
