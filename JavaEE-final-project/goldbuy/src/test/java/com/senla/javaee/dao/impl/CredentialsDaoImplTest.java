package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.CredentialsDao;
import com.senla.javaee.dao.DaoTest;
import com.senla.javaee.entity.Credentials;
import com.senla.javaee.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = CredentialsDaoImpl.class)
class CredentialsDaoImplTest extends DaoTest {
    @Resource
    private CredentialsDao credentialsDao;

    @BeforeEach
    public void fillingTable() {
        User user = User.builder().name("lesha").build();
        Credentials credentials = Credentials.builder().login("login").password("password").user(user).build();
        Credentials credentials2 = Credentials.builder().login("root").password("root123").user(user).build();
        Credentials credentials3 = Credentials.builder().login("admin").password("1234").user(user).build();
        credentialsDao.create(credentials);
        credentialsDao.create(credentials2);
        credentialsDao.create(credentials3);
    }

    @Test
    public void create() {
        User user = User.builder().name("lesha").build();
        Credentials credentials = Credentials.builder().login("root123").user(user).password("123").build();
        credentialsDao.create(credentials);
        Credentials credentials1 = credentialsDao.getById(4L);
        assertEquals(4L, credentials1.getId());
        assertEquals("root123", credentials1.getLogin());
        assertEquals("123", credentials1.getPassword());
    }

    @Test
    public void getById() {
        Credentials credentials = credentialsDao.getById(1L);
        assertEquals(1L, credentials.getId());
        assertEquals("login", credentials.getLogin());
        assertEquals("password", credentials.getPassword());
    }

    @Test
    public void update() {
        Credentials credentials = credentialsDao.getById(2L);
        assertEquals(2L, credentials.getId());
        assertEquals("root", credentials.getLogin());
        assertEquals("root123", credentials.getPassword());
        credentials.setLogin("toor");
        credentials.setPassword("321toor");
        credentialsDao.update(credentials);
        Credentials credentials1 = credentialsDao.getById(2L);
        assertEquals(2L, credentials1.getId());
        assertEquals("toor", credentials.getLogin());
        assertEquals("321toor", credentials.getPassword());
    }

    @Test
    public void delete() {
        credentialsDao.delete(3L);
        assertNull(credentialsDao.getById(3L));
    }

    @Test
    public void getCredentialsWithUser() {
        Credentials credentials = credentialsDao.getCredentialsWithUser(1L);
        assertEquals("lesha", credentials.getUser().getName());
        assertEquals("login", credentials.getLogin());
        assertEquals("password", credentials.getPassword());
    }
}