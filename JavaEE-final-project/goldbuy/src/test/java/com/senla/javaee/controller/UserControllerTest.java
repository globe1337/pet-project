package com.senla.javaee.controller;

import com.senla.javaee.dao.UserDao;
import com.senla.javaee.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends WebTest {
    @Resource
    private UserDao userDao;

    @Test
    public void getById() throws Exception {
        User user = userDao.create(User.builder().name("Lesha").build());
        mockMvc.perform(
                get("/users/" + user.getId())
        ).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()));
    }

    @Test
    public void deleteHistory() throws Exception {
        User user = userDao.create(User.builder().name("Lesha").build());
        mockMvc.perform(
                delete("/users/" + user.getId())
        ).andExpect(status().is2xxSuccessful());
        User user1 = userDao.getById(user.getId());
        assertNull(user1);

    }

    @Test
    public void create() throws Exception {
        final String roleInfoDto = """
                        {
                           "name": "Lesha"
                        }
                """;

        mockMvc.perform(
                post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(roleInfoDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists());
        User user = userDao.getById(1L);
        assertEquals("Lesha", user.getName());
        assertNotNull(user);
    }

    @Test
    public void update() throws Exception {
        User user = userDao.create(User.builder().name("Lesha").build());
        final String categoryUpdateDto = String.format("""
                {
                   "name": "oleg",
                   "id": %s
                }
                """, user.getId());

        mockMvc.perform(
                put("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryUpdateDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value("oleg"));

        User userUpdated = userDao.getById(user.getId());
        assertEquals(user.getId(), userUpdated.getId());
    }
}