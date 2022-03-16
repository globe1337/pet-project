package com.senla.javaee.controller;

import com.senla.javaee.dao.RoleDao;
import com.senla.javaee.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoleControllerTest extends WebTest {
    @Resource
    private RoleDao roleDao;

    @Test
    public void getById() throws Exception {
        Role role = roleDao.create(Role.builder().name("Admin").build());
        mockMvc.perform(
                get("/roles/" + role.getId())
        ).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id").value(role.getId()))
                .andExpect(jsonPath("$.name").value(role.getName()));
    }

    @Test
    public void deleteHistory() throws Exception {
        Role role = roleDao.create(Role.builder().name("Admin").build());
        mockMvc.perform(
                delete("/roles/" + role.getId())
        ).andExpect(status().is2xxSuccessful());
        Role role1 = roleDao.getById(role.getId());
        assertNull(role1);

    }

    @Test
    public void create() throws Exception {
        final String roleInfoDto = """
                        {
                           "name": "admin"                        
                        }
                """;

        mockMvc.perform(
                post("/roles/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(roleInfoDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists());
        Role role = roleDao.getById(1L);
        assertEquals("admin", role.getName());
        assertNotNull(role);
    }

    @Test
    public void update() throws Exception {
        Role role = roleDao.create(Role.builder().name("Admin").build());
        final String categoryUpdateDto = String.format("""
                {
                   "name": "Moderator",
                   "id": %s
                }
                """, role.getId());

        mockMvc.perform(
                put("/roles/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryUpdateDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(role.getId()))
                .andExpect(jsonPath("$.name").value("Moderator"));

        Role roleUpdated = roleDao.getById(role.getId());
        assertEquals(role.getId(), roleUpdated.getId());
    }
}