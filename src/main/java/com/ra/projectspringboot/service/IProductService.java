package com.ra.projectspringboot.service;

import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> findAllByCategoryId(Long categoryId);
    Product findById(Long productId) throws CustomException;
    Page<Product> findAllIsDeleteIsFalse(Pageable pageable);
    List<Product> searchByNameOrDescription(String search);
    List<Product> listOfFeaturedProducts();
    List<Product> newListProduct();
    List<Product> bestSellerProduct();
}
