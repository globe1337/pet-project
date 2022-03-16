package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.DaoTest;
import com.senla.javaee.dao.HistoryDao;
import com.senla.javaee.entity.History;
import com.senla.javaee.entity.Product;
import com.senla.javaee.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = HistoryDaoImpl.class)
class HistoryDaoImplTest extends DaoTest {
    private final Date date = new Date();
    private final float price = 400;
    @Resource
    private HistoryDao historyDao;

    @BeforeEach
    public void fillingTable() {
        History history = History.builder().sellingPrice(400).sellingDate(date)
                .product(Product.builder().id(1L).name("iphone").build())
                .customer(User.builder().id(1L).name("oleg").build()).build();
        History history2 = History.builder().sellingPrice(5454).sellingDate(date)
                .product(Product.builder().id(2L).build())
                .customer(User.builder().id(2L).build()).build();
        History history3 = History.builder().sellingPrice(6565).sellingDate(date)
                .product(Product.builder().id(2L).build())
                .customer(User.builder().id(2L).build()).build();
        historyDao.create(history);
        historyDao.create(history2);
        historyDao.create(history3);
    }

    @Test
    public void create() {
        History history = History.builder().sellingPrice(400).sellingDate(date).product(Product.builder().id(2L).build()).build();
        historyDao.create(history);
        History history1 = historyDao.getById(4L);
        assertEquals(4L, history1.getId());
        assertEquals(history.getSellingPrice(), history1.getSellingPrice());
        assertEquals(history.getSellingDate(), history1.getSellingDate());
        assertEquals(history.getCustomer(), history1.getCustomer());
        assertEquals(history.getProduct(), history1.getProduct());
    }

    @Test
    public void getById() {
        History history = historyDao.getById(1L);
        assertEquals(1L, history.getId());
        assertEquals(price, history.getSellingPrice());
        assertEquals(date, history.getSellingDate());
    }

    @Test
    public void update() {
        History history = historyDao.getById(2L);
        history.setSellingPrice(400);
        historyDao.update(history);
        History history1 = historyDao.getById(2L);
        assertEquals(2L, history1.getId());
        assertEquals(history.getSellingPrice(), history1.getSellingPrice());
    }

    @Test
    public void delete() {
        historyDao.delete(2L);
        assertNull(historyDao.getById(2L));
    }

    @Test
    public void getHistoryWithCustomer() {
        History history = historyDao.getHistoryWithCustomer(1L);
        assertEquals(1L, history.getId());
        assertEquals(price, history.getSellingPrice());
        assertEquals(date, history.getSellingDate());
        assertEquals("oleg", history.getCustomer().getName());
        assertEquals(1L, history.getCustomer().getId());
    }

    @Test
    public void getHistoryWithProduct() {
        History history = historyDao.getHistoryWithProduct(1L);
        assertEquals(1L, history.getId());
        assertEquals(price, history.getSellingPrice());
        assertEquals(date, history.getSellingDate());
        assertEquals("iphone", history.getProduct().getName());
        assertEquals(1L, history.getProduct().getId());
    }


}