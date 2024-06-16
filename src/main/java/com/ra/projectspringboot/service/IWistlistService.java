package com.ra.projectspringboot.service;

import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.WishList;

import java.util.List;

public interface IWistlistService {

    List<WishList> getAllWishlist();
    void addProductToWishist(Long productId) throws CustomException;
    void removeProductInWishlist(Long wishlistId) throws CustomException;

}
