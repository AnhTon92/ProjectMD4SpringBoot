package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.ShopingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingCartRepository extends JpaRepository<ShopingCart,Long> {
}
