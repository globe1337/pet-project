package com.senla.javaee.controller;

import com.senla.javaee.dao.ProductConfigurationDao;
import com.senla.javaee.entity.ProductConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductConfigurationControllerTest extends WebTest {
    @Resource
    private ProductConfigurationDao productConfigurationDao;

    @Test
    public void getById() throws Exception {
        ProductConfiguration productConfiguration = productConfigurationDao.create(ProductConfiguration.builder().maxPrice(200).build());
        mockMvc.perform(
                get("/product-configurations/" + productConfiguration.getId())
        ).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id").value(productConfiguration.getId()))
                .andExpect(jsonPath("$.maxPrice").value(productConfiguration.getMaxPrice()));
    }

    @Test
    public void deleteHistory() throws Exception {
        ProductConfiguration productConfiguration = productConfigurationDao.create(ProductConfiguration.builder().maxPrice(200).build());
        mockMvc.perform(
                delete("/product-configurations/" + productConfiguration.getId())
        ).andExpect(status().is2xxSuccessful());
        ProductConfiguration productConfiguration1 = productConfigurationDao.getById(productConfiguration.getId());
        assertNull(productConfiguration1);
    }

    @Test
    public void create() throws Exception {

        final String productConfigurationDto = """
                        {
                           "maxPrice": "200.0"
                        }
                """;

        mockMvc.perform(
                post("/product-configurations/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(productConfigurationDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists());

        ProductConfiguration productConfiguration = productConfigurationDao.getById(1L);
        assertEquals(200.0, productConfiguration.getMaxPrice());
        assertNotNull(productConfiguration);
    }

    @Test
    public void update() throws Exception {
        ProductConfiguration productConfiguration = productConfigurationDao.create(ProductConfiguration.builder().maxPrice(200).build());
        final String categoryUpdateDto = String.format("""
                {
                   "maxPrice": "50",
                   "id": %s
                }
                """, productConfiguration.getId());

        mockMvc.perform(
                put("/product-configurations/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryUpdateDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(productConfiguration.getId()))
                .andExpect(jsonPath("$.maxPrice").value(50.0));
        ProductConfiguration productConfigurationUpdated = productConfigurationDao.getById(productConfiguration.getId());
        assertEquals(productConfiguration.getId(), productConfigurationUpdated.getId());
    }

}