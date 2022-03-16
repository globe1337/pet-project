package com.senla.javaee.service.impl;

import com.senla.javaee.dao.CategoryDao;
import com.senla.javaee.dto.category.CategoryInfoDto;
import com.senla.javaee.dto.category.CategoryWithProductDto;
import com.senla.javaee.entity.Category;
import com.senla.javaee.service.CategoryService;
import com.senla.javaee.service.converter.CategoryConverter;
import com.senla.javaee.service.exception.category.CategoryNotCreatedException;
import com.senla.javaee.service.exception.category.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import static java.util.Optional.ofNullable;

@Component
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;
    private final CategoryConverter categoryConverter;


    @Override
    public CategoryInfoDto delete(Long id) {
        return categoryConverter.convert(categoryDao.delete(id));
    }

    @Override
    public CategoryInfoDto create(CategoryInfoDto categoryDto) {
        Category category=categoryConverter.convert(categoryDto);
        Category category1 = ofNullable(categoryDao.create(category))
                .orElseThrow(() -> new CategoryNotCreatedException(categoryDto.getName()));
        return categoryConverter.convert(category1);
    }


    @Override
    public CategoryInfoDto update(CategoryInfoDto categoryDto) {
        Category category = categoryConverter.convert(categoryDto);
        return categoryConverter.convert(categoryDao.update(category));
    }

    @Override
    public CategoryInfoDto getById(Long id) {
        Category category = ofNullable(categoryDao.getById(id))
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryConverter.convert(category);
    }

    @Override
    public CategoryInfoDto getByName(String name) {
        Category category = ofNullable(categoryDao.getByName(name))
                .orElseThrow(() -> new CategoryNotFoundException(name));
        return categoryConverter.convert(category);
    }

    @Override
    public CategoryWithProductDto getCategoryWithProduct(Long id) {
        return categoryConverter.covert(categoryDao.getCategoryWithProduct(id));
    }
}
