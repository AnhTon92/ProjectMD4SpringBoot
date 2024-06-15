package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
