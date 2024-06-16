package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.model.entity.Category;
import com.ra.projectspringboot.repository.ICategoryRepository;
import com.ra.projectspringboot.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategoryStatusIsTrue() {
        return categoryRepository.findAllByStatusIsTrue();
    }
}
