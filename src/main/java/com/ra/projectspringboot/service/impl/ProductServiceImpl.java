package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Product;
import com.ra.projectspringboot.repository.IProductRepository;
import com.ra.projectspringboot.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;

    @Override
    public List<Product> findAllByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Product findById(Long productId) throws CustomException {
        return productRepository.findById(productId).orElseThrow(() -> new CustomException("product not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Product> findAllIsDeleteIsFalse(Pageable pageable) {
        return productRepository.findAllByIsDeleteIsFalse(pageable);
    }

    @Override
    public List<Product> searchByNameOrDescription(String search) {
        return productRepository.findAllByProductNameContainsOrDescriptionContains(search, search);
    }

    @Override
    public List<Product> listOfFeaturedProducts() {
        return new ArrayList<>(productRepository.listOfFeaturedProducts());
    }

    @Override
    public List<Product> newListProduct() {
        return productRepository.findTop5ByCreatedAtOrderByDesc().stream().limit(5).toList();
    }

    @Override
    public List<Product> bestSellerProduct() {
        return new ArrayList<>(productRepository.bestSellerProducts());
    }
}
