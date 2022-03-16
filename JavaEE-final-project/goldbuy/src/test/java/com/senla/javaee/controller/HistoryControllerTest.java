package com.senla.javaee.controller;

import com.senla.javaee.dao.HistoryDao;
import com.senla.javaee.entity.History;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoryControllerTest extends WebTest {
    @Resource
    private HistoryDao historyDao;

    @Test
    public void getById() throws Exception {
        History history = historyDao.create(History.builder().sellingPrice(200).build());
        mockMvc.perform(
                get("/histories/" + history.getId())
        ).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id").value(history.getId()))
                .andExpect(jsonPath("$.sellingPrice").value(history.getSellingPrice()));
    }

    @Test
    public void deleteHistory() throws Exception {
        History history = historyDao.create(History.builder().sellingPrice(200).build());
        mockMvc.perform(
                delete("/histories/" + history.getId())
        ).andExpect(status().is2xxSuccessful());
        History history1 = historyDao.getById(history.getId());
        assertNull(history1);

    }

    @Test
    public void create() throws Exception {
        final String credentialsDto = """
                        {
                           "sellingPrice": "450"
                        }
                """;

        mockMvc.perform(
                post("/histories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(credentialsDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists());

        History historys = historyDao.getById(1L);
        assertEquals(450.0, historys.getSellingPrice());
        assertNotNull(historys);
    }

    @Test
    public void update() throws Exception {
        History history = historyDao.create(History.builder().sellingPrice(200).build());
        final String categoryUpdateDto = String.format("""
                {
                   "sellingPrice": "50",
                   "id": %s
                }
                """, history.getId());

        mockMvc.perform(
                put("/histories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryUpdateDto)
        ).andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(history.getId()))
                .andExpect(jsonPath("$.sellingPrice").value(50.0));
        History historyUpdated = historyDao.getById(history.getId());
        assertEquals(history.getId(), historyUpdated.getId());
    }
}