package com.senla.javaee.controller;

import com.senla.javaee.dao.CategoryDao;
import com.senla.javaee.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest extends WebTest {
    @Resource
    private CategoryDao categoryDao;

    @Test
    public void getById() throws Exception {
        Category category = categoryDao.create(Category.builder().name("phone").build());
        mockMvc.perform(
                get("/categories/" + category.getId())
        ).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id").value(category.getId()))
                .andExpect(jsonPath("$.name").value(category.getName()));
    }

    @Test
    public void deleteCategory() throws Exception {
        Category category = categoryDao.create(Category.builder().name("phone").build());
        mockMvc.perform(
                delete("/categories/" + category.getId())
        ).andExpect(status().is2xxSuccessful());
        Category category1 = categoryDao.getById(category.getId());
        assertNull(category1);
    }

    @Test
    public void create() throws Exception {
        final String categoryDto = """
                        {
                           "name": "phones"
                        }
                """;

        mockMvc.perform(
                post("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(categoryDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists());
        assertEquals("phones", categoryDao.getById(1L).getName());
        assertNotNull(categoryDao.getById(1L));
    }

    @Test
    public void update() throws Exception {
        Category category = categoryDao.create(Category.builder().name("phone").build());

        final String categoryUpdateDto = String.format("""
                {
                   "name": "cars",
                   "id": %s
                }
                """, category.getId());

        mockMvc.perform(
                put("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryUpdateDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(category.getId()))
                .andExpect(jsonPath("$.name").value("cars"));
        Category categoryUpdated = categoryDao.getById(category.getId());
        assertEquals(category.getId(), categoryUpdated.getId());
    }


}