package com.ra.projectspringboot.service;

import com.ra.projectspringboot.model.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategoryStatusIsTrue();
}
