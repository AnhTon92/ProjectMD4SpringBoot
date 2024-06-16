package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IWishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserId(Long userId);

    boolean existsByProductIdAndUserId(Long productId, Long userId);

    void deleteByProductIdAndUserId(Long productId, Long userId);

}
