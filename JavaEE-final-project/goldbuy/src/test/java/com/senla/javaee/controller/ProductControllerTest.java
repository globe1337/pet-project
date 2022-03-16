package com.senla.javaee.controller;

import com.senla.javaee.dao.ProductDao;
import com.senla.javaee.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends WebTest {
    @Resource
    private ProductDao productDao;

    @Test
    public void getById() throws Exception {
        Product product = productDao.create(Product.builder().name("iphone").price(200).build());
        mockMvc.perform(
                get("/products/" + product.getId())
        ).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.name").value(product.getName()));
    }

    @Test
    public void deleteHistory() throws Exception {
        Product product = productDao.create(Product.builder().name("iphone").price(200).build());
        mockMvc.perform(
                delete("/products/" + product.getId())
        ).andExpect(status().is2xxSuccessful());
        Product product1 = productDao.getById(product.getId());
        assertNull(product1);

    }

    @Test
    public void create() throws Exception {
        final String productConfigurationDto = """
                        {
                           "price": "450",
                           "name": "iphone"                        
                        }
                """;

        mockMvc.perform(
                post("/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(productConfigurationDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists());

        Product product = productDao.getById(1L);
        assertEquals(450.0, product.getPrice());
        assertEquals("iphone", product.getName());
        assertNotNull(product);
    }

    @Test
    public void update() throws Exception {
        Product product = productDao.create(Product.builder().name("iphone").price(200).build());
        final String categoryUpdateDto = String.format("""
                {
                   "price": "50",
                   "name": "samsung",
                   "id": %s
                }
                """, product.getId());

        mockMvc.perform(
                put("/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryUpdateDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.price").value(50.0))
                .andExpect(jsonPath("$.name").value("samsung"));

        Product productUpdated = productDao.getById(product.getId());
        assertEquals(product.getId(), productUpdated.getId());
    }
}