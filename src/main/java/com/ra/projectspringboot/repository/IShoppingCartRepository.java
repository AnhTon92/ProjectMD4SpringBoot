package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.ShopingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface IShoppingCartRepository extends JpaRepository<ShopingCart, Long> {
    List<ShopingCart> findAllByUserId(Long userId);

    Optional<ShopingCart> findByUserIdAndProductId(Long userId, Long productId);

    void deleteAllByUserId(Long userId);
}
