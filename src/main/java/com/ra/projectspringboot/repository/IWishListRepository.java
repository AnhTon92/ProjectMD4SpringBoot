package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWishListRepository extends JpaRepository<WishList,Long> {
}
