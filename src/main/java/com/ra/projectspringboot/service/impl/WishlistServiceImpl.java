package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.WishList;
import com.ra.projectspringboot.repository.IWishListRepository;
import com.ra.projectspringboot.service.IProductService;
import com.ra.projectspringboot.service.IUserService;
import com.ra.projectspringboot.service.IWistlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements IWistlistService {
    private final IWishListRepository wishListRepository;
    private final IUserService userService;
    private final IProductService productService;

    @Override
    public List<WishList> getAllWishlist() {
        return wishListRepository.findAllByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public void addProductToWishist(Long productId) throws CustomException {
        if (wishListRepository.existsByProductIdAndUserId(productId, userService.getCurrentUser().getId())) {
            wishListRepository.deleteByProductIdAndUserId(productId, userService.getCurrentUser().getId());
        } else {
            WishList wishList = WishList.builder()
                    .product(productService.findProductById(productId))
                    .user(userService.findUserById(userService.getCurrentUser().getId()))
                    .build();
            wishListRepository.save(wishList);
        }
    }

    @Override
    public void removeProductInWishlist(Long wishlistId) throws CustomException {
        if (wishListRepository.existsById(wishlistId)) {
            wishListRepository.deleteById(wishlistId);
        } else {
            throw new CustomException("wishlist not found", HttpStatus.NOT_FOUND);
        }
    }
}
