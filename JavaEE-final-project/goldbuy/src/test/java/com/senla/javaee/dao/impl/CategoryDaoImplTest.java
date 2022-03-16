package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.CategoryDao;
import com.senla.javaee.dao.DaoTest;
import com.senla.javaee.entity.Category;
import com.senla.javaee.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = CategoryDaoImpl.class)
class CategoryDaoImplTest extends DaoTest {
    @Resource
    private CategoryDao categoryDao;

    @BeforeEach
    public void fillingTable() {
        Product product = Product.builder().name("iphone").build();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Category category = Category.builder().products(productList).name("phone").build();
        Category category2 = Category.builder().name("car").build();
        Category category3 = Category.builder().name("boat").build();
        categoryDao.create(category);
        categoryDao.create(category2);
        categoryDao.create(category3);
    }

    @Test
    public void getCategoryWithProduct() {
        Category category = categoryDao.getCategoryWithProduct(1L);
        assertEquals(1L, category.getId());
        assertEquals("phone", category.getName());
        assertEquals("iphone", category.getProducts().get(0).getName());
    }

    @Test
    public void create() {
        Category category = Category.builder().name("pc").build();
        categoryDao.create(category);
        Category category1 = categoryDao.getById(4L);
        assertEquals(4L, category1.getId());
        assertEquals("pc", category1.getName());
    }

    @Test
    public void getById() {
        Category category1 = categoryDao.getById(1L);
        assertEquals(1L, category1.getId());
        assertEquals("phone", category1.getName());

    }

    @Test
    public void update() {
        Category category = categoryDao.getById(2L);
        assertEquals(2L, category.getId());
        assertEquals("car", category.getName());
        category.setName("notebook");
        categoryDao.update(category);
        Category category2 = categoryDao.getById(2L);
        assertEquals(2L, category2.getId());
        assertEquals("notebook", category2.getName());
    }

    @Test
    public void delete() {
        categoryDao.delete(3L);
        assertNull(categoryDao.getById(3L));
    }
}