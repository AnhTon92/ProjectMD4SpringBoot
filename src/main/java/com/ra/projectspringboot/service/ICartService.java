package com.ra.projectspringboot.service;

import com.ra.projectspringboot.dto.request.CartItemRequest;
import com.ra.projectspringboot.dto.request.CheckoutRequest;
import com.ra.projectspringboot.dto.response.CartItemResponse;
import com.ra.projectspringboot.exception.CustomException;

import java.util.List;

public interface ICartService {
    List<CartItemResponse> findAllCartItem();

    CartItemResponse addNewCartItem(CartItemRequest cartItemRequest) throws CustomException;

    CartItemResponse changeQuantity(Long cartItemId,Integer quantity) throws CustomException;

    void deleteCartItemById(Long cartItemId) throws CustomException;

    void deleteAllCartItem();

    void checkout(CheckoutRequest checkoutRequest) throws CustomException;
}
