package com.senla.javaee.controller;

import com.senla.javaee.dao.CredentialsDao;
import com.senla.javaee.entity.Credentials;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CredentialsControllerTest extends WebTest {
    @Resource
    private CredentialsDao credentialsDao;

    @Test
    public void getById() throws Exception {
        Credentials credentials = credentialsDao.create(Credentials.builder().login("asd123").build());
        mockMvc.perform(
                get("/credentials/" + credentials.getId())
        ).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id").value(credentials.getId()))
                .andExpect(jsonPath("$.login").value(credentials.getLogin()));
    }

    @Test
    public void deleteCredentials() throws Exception {
        Credentials credentials = credentialsDao.create(Credentials.builder().login("asd123").build());
        mockMvc.perform(
                delete("/credentials/" + credentials.getId())
        ).andExpect(status().is2xxSuccessful());
        Credentials credentials1 = credentialsDao.getById(credentials.getId());
        assertNull(credentials1);

    }

    @Test
    public void create() throws Exception {
        final String credentialsDto = """
                        {
                           "login": "asd123"
                        }
                """;

        mockMvc.perform(
                post("/credentials/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(credentialsDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists());

        Credentials credentials = credentialsDao.getById(1L);
        assertEquals("asd123", credentials.getLogin());
        assertNotNull(credentials);
    }

    @Test
    public void update() throws Exception {
        Credentials credentials = credentialsDao.create(Credentials.builder().login("asd123").build());

        final String categoryUpdateDto = String.format("""
                {
                   "login": "root",
                   "id": %s
                }
                """, credentials.getId());

        mockMvc.perform(
                put("/credentials/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryUpdateDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(credentials.getId()))
                .andExpect(jsonPath("$.login").value("root"));
        Credentials credentialsUpdated = credentialsDao.getById(credentials.getId());
        assertEquals(credentials.getId(), credentialsUpdated.getId());
    }
}