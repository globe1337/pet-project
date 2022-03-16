package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.DaoTest;
import com.senla.javaee.dao.ProductConfigurationDao;
import com.senla.javaee.entity.Product;
import com.senla.javaee.entity.ProductConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = ProductConfigurationDaoImpl.class)
class ProductConfigurationDaoImplTest extends DaoTest {
    private final float minPrice = 200;
    private final float maxPrice = 450;
    private final float priceStep = 20;
    @Resource
    ProductConfigurationDao productConfigurationDao;

    @BeforeEach
    public void fillingTable() {
        ProductConfiguration productConfiguration = ProductConfiguration
                .builder().frequency(10).maxPrice(maxPrice).minPrice(minPrice).priceStep(priceStep)
                .product(Product.builder().id(1L).name("iphone").build()).build();
        ProductConfiguration productConfiguration2 = ProductConfiguration
                .builder().frequency(20).maxPrice(maxPrice).minPrice(minPrice).priceStep(priceStep)
                .product(Product.builder().id(2L).name("samsung").build()).build();
        ProductConfiguration productConfiguration3 = ProductConfiguration
                .builder().frequency(30).maxPrice(maxPrice).minPrice(minPrice).priceStep(priceStep)
                .product(Product.builder().id(3L).name("sony").build()).build();
        productConfigurationDao.create(productConfiguration);
        productConfigurationDao.create(productConfiguration2);
        productConfigurationDao.create(productConfiguration3);
    }

    @Test
    public void create() {
        ProductConfiguration productConfiguration = productConfigurationDao.create(ProductConfiguration
                .builder().frequency(40).maxPrice(maxPrice).minPrice(minPrice).priceStep(priceStep)
                .product(Product.builder().id(4L).name("audi").build()).build());
        ProductConfiguration productConfiguration1 = productConfigurationDao.getById(4L);
        assertEquals(4L, productConfiguration1.getId());
        assertEquals(productConfiguration.getMaxPrice(), productConfiguration1.getMaxPrice());
        assertEquals(productConfiguration.getMinPrice(), productConfiguration1.getMinPrice());
        assertEquals(productConfiguration.getFrequency(), productConfiguration1.getFrequency());
    }

    @Test
    public void getById() {
        ProductConfiguration productConfiguration = productConfigurationDao.getById(1L);
        assertEquals(1L, productConfiguration.getId());
        assertEquals(maxPrice, productConfiguration.getMaxPrice());
        assertEquals(minPrice, productConfiguration.getMinPrice());
        assertEquals(10, productConfiguration.getFrequency());
    }

    @Test
    public void update() {
        ProductConfiguration productConfiguration = productConfigurationDao.getById(2L);
        productConfiguration.setFrequency(12345);
        productConfigurationDao.update(productConfiguration);
        assertEquals(2L, productConfiguration.getId());
        assertEquals(maxPrice, productConfiguration.getMaxPrice());
        assertEquals(minPrice, productConfiguration.getMinPrice());
        assertEquals(12345, productConfiguration.getFrequency());
    }

    @Test
    public void delete() {
        productConfigurationDao.delete(3L);
        assertNull(productConfigurationDao.getById(3L));
    }

    @Test
    public void getProductConfigWithProduct() {
        ProductConfiguration productConfiguration = productConfigurationDao.getProductConfigWithProduct(1L);
        assertEquals(1L, productConfiguration.getId());
        assertEquals(1L, productConfiguration.getProduct().getId());
        assertEquals("iphone", productConfiguration.getProduct().getName());
        assertEquals(maxPrice, productConfiguration.getMaxPrice());
        assertEquals(minPrice, productConfiguration.getMinPrice());
    }

}