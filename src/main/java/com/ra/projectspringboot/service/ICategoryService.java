package com.ra.projectspringboot.service;

import com.ra.projectspringboot.dto.request.CategoryRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategoryStatusIsTrue();


    Page<Category> findAllCategory(Pageable pageable);

    Category findCategoryById(Long categoryId) throws CustomException;

    Category addNewCategory(CategoryRequest categoryRequest) throws CustomException;

    Category updateCategory(CategoryRequest categoryRequest, Long categoryId) throws CustomException;

    void deleteCategoryById(Long categoryId) throws CustomException;

}
