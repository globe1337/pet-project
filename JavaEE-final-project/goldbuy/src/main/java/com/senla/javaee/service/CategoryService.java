package com.senla.javaee.service;

import com.senla.javaee.dto.category.CategoryInfoDto;
import com.senla.javaee.dto.category.CategoryWithProductDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryService {
    CategoryInfoDto delete(Long id);

    CategoryInfoDto create(CategoryInfoDto categoryDao);

    CategoryInfoDto update(CategoryInfoDto categoryDao);

    CategoryInfoDto getById(Long id);

    CategoryWithProductDto getCategoryWithProduct(Long id);

    CategoryInfoDto getByName(String name);
}
