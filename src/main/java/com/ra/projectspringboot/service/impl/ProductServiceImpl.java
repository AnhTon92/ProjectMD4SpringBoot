package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.dto.request.CategoryRequest;
import com.ra.projectspringboot.dto.request.ProductRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Category;
import com.ra.projectspringboot.model.entity.Product;
import com.ra.projectspringboot.repository.IProductRepository;
import com.ra.projectspringboot.service.ICategoryService;
import com.ra.projectspringboot.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final ICategoryService categoryService;

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

    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findProductById(Long productId) throws CustomException {
        return productRepository.findById(productId).orElseThrow(() -> new CustomException("product not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Product addNewProduct(ProductRequest productRequest) throws CustomException {
        if (productRepository.existsByProductName(productRequest.getProductName())) {
            throw new CustomException("productName already exist", HttpStatus.CONFLICT);
        }
        return productRepository.save(toEntity(productRequest));
    }

    @Override
    public Product updateProduct(ProductRequest productRequest, Long productId) throws CustomException {
        Product oldProduct = findProductById(productId);
        if (oldProduct != null) {
            if (
                    !oldProduct.getProductName().equals(productRequest.getProductName()) &&
                            productRepository.existsByProductName(productRequest.getProductName())
            ) {
                throw new CustomException("productName already exist", HttpStatus.CONFLICT);
            }
            Product product = toEntity(productRequest);
            product.setId(productId);
            return productRepository.save(product);
        }
        throw new CustomException("product not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteProductById(Long productId) throws CustomException {
        Product product = findProductById(productId);
        product.setIsDelete(!product.getIsDelete());
        productRepository.save(product);
    }

    public Product toEntity(ProductRequest productRequest) throws CustomException {
        return Product.builder()
                .sku(UUID.randomUUID().toString())
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .unitPrice(productRequest.getUnitPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .category(categoryService.findCategoryById(productRequest.getCategoryId()))
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDelete(false)
                .build();
    }
}
