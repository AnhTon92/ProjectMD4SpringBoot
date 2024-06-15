package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
