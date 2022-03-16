package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.DaoTest;
import com.senla.javaee.dao.ProductDao;
import com.senla.javaee.entity.Category;
import com.senla.javaee.entity.Product;
import com.senla.javaee.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = ProductDaoImpl.class)
class ProductDaoImplTest extends DaoTest {
    private final float price = 200;
    private final Date date = new Date();
    @Resource
    ProductDao productDao;

    @BeforeEach
    public void fillingTable() {
        Product product = Product.builder()
                .status("active").price(price).name("iphone").addedDate(date).build();
        Product product2 = Product.builder()
                .status("active").price(2000).name("audi").addedDate(date).build();
        Product product3 = Product.builder()
                .status("sell").price(price).name("sneaker").addedDate(date).build();
        productDao.create(product);
        productDao.create(product2);
        productDao.create(product3);
    }

    @Test
    public void create() {
        Product product = Product.builder()
                .category(Category.builder().id(4L).name("wheels").build())
                .user(User.builder().id(4L).name("kiril").build())
                .price(price).name("belshina").addedDate(date).build();
        productDao.create(product);
        Product product1 = productDao.getById(4L);
        assertEquals(4L, product1.getId());
        assertEquals(product.getPrice(), product1.getPrice());
        assertEquals(product.getAddedDate(), product1.getAddedDate());
        assertEquals(product.getName(), product1.getName());
    }

    @Test
    public void getById() {
        Product product = productDao.getById(1L);
        assertEquals(1L, product.getId());
        assertEquals(price, product.getPrice());
        assertEquals("iphone", product.getName());
        assertEquals(date, product.getAddedDate());
    }

    @Test
    public void update() {
        Product product = productDao.getById(2L);
        product.setName("mercedes");
        productDao.update(product);
        Product product1 = productDao.getById(2L);
        assertEquals(2L, product1.getId());
        assertEquals("mercedes", product1.getName());

    }

    @Test
    public void delete() {
        productDao.delete(3L);
        assertNull(productDao.getById(3L));
    }

    @Test
    public void getProductWithUser() {
        productDao.create(Product.builder()
                .user(User.builder().id(4L).name("kiril").build())
                .price(price).name("belshina").addedDate(date).build());
        Product product = productDao.getProductWithUser(4L);
        assertEquals(4L, product.getId());
        assertEquals("belshina", product.getName());
        assertEquals(4L, product.getUser().getId());
        assertEquals("kiril", product.getUser().getName());
    }

    @Test
    public void getProductWithCategory() {
        productDao.create(Product.builder()
                .category(Category.builder().id(4L).name("wheels").build())
                .price(price).name("belshina").addedDate(date).build());
        Product product = productDao.getProductWithCategory(4L);
        assertEquals(4L, product.getId());
        assertEquals("belshina", product.getName());
        assertEquals(4L, product.getCategory().getId());
        assertEquals("wheels", product.getCategory().getName());
    }

    @Test
    public void getMostExpensiveProduct() {
        Product product = productDao.getMostExpensiveProduct();
        float price = 2000;
        assertEquals(2L, product.getId());
        assertEquals("audi", product.getName());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void getActiveProducts() {
        List<Product> productList = productDao.getActiveProducts();
        System.out.println();
        assertEquals(1L, productList.get(0).getId());
        assertEquals("iphone", productList.get(0).getName());
        assertEquals(2, productList.size());
        assertEquals(2L, productList.get(1).getId());
        assertEquals("audi", productList.get(1).getName());
    }

}