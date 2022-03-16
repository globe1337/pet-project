package com.senla.javaee.service.impl;

import com.senla.javaee.dao.CategoryDao;
import com.senla.javaee.dto.category.CategoryInfoDto;
import com.senla.javaee.dto.category.CategoryWithProductDto;
import com.senla.javaee.entity.Category;
import com.senla.javaee.service.converter.CategoryConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
 class CategoryServiceImplTest {
    private final String name = "phone";
    private final Long id = 1L;
    private final Category category = Category.builder().id(id).name(name).build();
    @Spy
    private ModelMapper mapper;
    @Spy
    @InjectMocks
    private CategoryConverter categoryConverter;
    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private CategoryDao categoryDao;

    @Test
    public void testCreate() {
        when(categoryDao.create(any())).thenReturn(category);
        CategoryInfoDto categoryInfoDto = categoryService.create(CategoryInfoDto.builder().name(name).build());
        assertEquals(id, categoryInfoDto.getId());
        assertEquals(name, categoryInfoDto.getName());
    }


    @Test
    public void testDelete() {
        when(categoryDao.delete(any())).thenReturn(category);
        CategoryInfoDto categoryInfoDto = categoryService.delete(id);
        assertEquals(id, categoryInfoDto.getId());
        assertEquals(name, categoryInfoDto.getName());
    }

    @Test
    public void testUpdate() {
        when(categoryDao.update(any())).thenReturn(category);
        CategoryInfoDto categoryInfoDto = categoryService.update(CategoryInfoDto.builder().name(name).build());
        assertEquals(id, categoryInfoDto.getId());
        assertEquals(name, categoryInfoDto.getName());
    }

    @Test
    public void testGetById() {
        when(categoryDao.getById(any())).thenReturn(Category.builder().name(name).id(1L).build());
        CategoryInfoDto categoryInfoDto = categoryService.getById(1L);
        assertEquals(id, categoryInfoDto.getId());
        assertEquals(name, categoryInfoDto.getName());
    }

    @Test
    public void getCategoryWithProduct() {
        when(categoryDao.getCategoryWithProduct(any())).thenReturn(category);
        CategoryWithProductDto categoryWithProductDto = categoryService.getCategoryWithProduct(id);
        assertEquals(id, categoryWithProductDto.getCategoryInfoDto().getId());
        assertEquals(name, categoryWithProductDto.getCategoryInfoDto().getName());
    }
}