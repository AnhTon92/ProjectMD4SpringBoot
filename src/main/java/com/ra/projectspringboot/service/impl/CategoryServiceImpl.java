package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.dto.request.CategoryRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Category;
import com.ra.projectspringboot.repository.ICategoryRepository;
import com.ra.projectspringboot.repository.IProductRepository;
import com.ra.projectspringboot.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;

    @Override
    public List<Category> findAllCategoryStatusIsTrue() {
        return categoryRepository.findAllByStatusIsTrue();
    }

    @Override
    public Page<Category> findAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findCategoryById(Long categoryId) throws CustomException {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CustomException("category not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Category addNewCategory(CategoryRequest categoryRequest) throws CustomException {
        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())) {
            throw new CustomException("categoryName already exist", HttpStatus.CONFLICT);
        }
        return categoryRepository.save(toEntity(categoryRequest));
    }

    @Override
    public Category updateCategory(CategoryRequest categoryRequest, Long categoryId) throws CustomException {
        Category oldCategory = findCategoryById(categoryId);
        if (oldCategory != null) {
            if (
                    !oldCategory.getCategoryName().equals(categoryRequest.getCategoryName()) &&
                            categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())
            ) {
                throw new CustomException("categoryName already exist", HttpStatus.CONFLICT);
            }
            Category category = toEntity(categoryRequest);
            category.setId(categoryId);
            return categoryRepository.save(category);
        }
        throw new CustomException("category not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteCategoryById(Long categoryId) throws CustomException {
        if (productRepository.existsByCategoryId(categoryId)) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CustomException("category not found", HttpStatus.NOT_FOUND));
            category.setStatus(!category.getStatus());
            categoryRepository.save(category);
        } else {
            categoryRepository.deleteById(categoryId);
        }
    }

    public Category toEntity(CategoryRequest categoryRequest) {
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .description(categoryRequest.getDescription())
                .status(true)
                .build();
    }

}
