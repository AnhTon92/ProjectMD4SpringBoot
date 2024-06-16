package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByStatusIsTrue();
    boolean existsByCategoryName(String categoryName);

}
