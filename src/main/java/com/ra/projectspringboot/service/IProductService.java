package com.ra.projectspringboot.service;

import com.ra.projectspringboot.dto.request.ProductRequest;
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

    Page<Product> findAllProduct(Pageable pageable);

    Product findProductById(Long productId) throws CustomException;

    Product addNewProduct(ProductRequest productRequest) throws CustomException;

    Product updateProduct(ProductRequest productRequest, Long productId) throws CustomException;

    void deleteProductById(Long productId) throws CustomException;
}
