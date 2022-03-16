package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.DaoTest;
import com.senla.javaee.dao.RoleDao;
import com.senla.javaee.dao.UserDao;
import com.senla.javaee.entity.Credentials;
import com.senla.javaee.entity.Product;
import com.senla.javaee.entity.Role;
import com.senla.javaee.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = UserDaoImpl.class)
class UserDaoImplTest extends DaoTest {
    @Resource
    UserDao userDao;
    @Resource
    RoleDao roleDao;

    @BeforeEach
    public void fillingTable() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().id(1L).name("Admin").build());
        roleDao.create(Role.builder().name("Admin").build());
        User user = User.builder().name("john")
                .credentials(Credentials.builder().login("123").build()).build();
        User user1 = User.builder().name("oleg").mail("olegMail").phoneNumber("123")
                .credentials(Credentials.builder().login("lol123").build())
                .roles(roles).build();
        User user2 = User.builder().name("sasha").mail("sashaMail").phoneNumber("321")
                .credentials(Credentials.builder().login("kek").build()).build();
        userDao.create(user);
        userDao.create(user1);
        userDao.create(user2);
    }

    @Test
    public void create() {
        User user = User.builder().name("vitya").build();
        userDao.create(user);
        User user1 = userDao.getById(8L);
        assertEquals(8L, user1.getId());
        assertEquals(user.getName(), user1.getName());

    }

    @Test
    public void getById() {
        User user = userDao.getById(4L);
        assertEquals(4L, user.getId());
        assertEquals("oleg", user.getName());
    }

    @Test
    public void update() {
        User user = userDao.getById(2L);
        user.setName("kiril");
        userDao.update(user);
        User user1 = userDao.getById(2L);
        assertEquals(2L, user1.getId());
        assertEquals("kiril", user1.getName());
    }

    @Test
    public void delete() {
        userDao.delete(6L);
        assertNull(userDao.getById(6L));
    }


    @Test
    public void getUserWithCredentials() {
        userDao.create(User.builder().name("steven")
                .credentials(Credentials.builder().login("login").build()).build());
        User user = userDao.getUserWithCredentials(8L);
        assertEquals("steven", user.getName());
        assertEquals(8L, user.getId());
        assertEquals("login", user.getCredentials().getLogin());

    }

    @Test
    public void getUserWithProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder().name("iphone").build());
        userDao.create(User.builder().name("steven")
                .products(productList).build());
        User user = userDao.getUserWithCredentials(8L);
        assertEquals("steven", user.getName());
        assertEquals(8L, user.getId());
        assertEquals("iphone", user.getProducts().get(0).getName());

    }

    @Test
    public void getUserWithRole() {
        User user = userDao.getUserWithRole(4L);
        assertEquals("Admin", user.getRoles().get(0).getName());
        assertEquals("oleg", user.getName());

    }

    @Test
    public void getUserWithLogin() {
        User user = userDao.getUserWithLogin("lol123");
        assertEquals("lol123", user.getCredentials().getLogin());
        assertEquals("oleg", user.getName());
    }

    @Test
    public void getAllAdmin() {
        List<User> users = userDao.getAllAdmin();
        assertEquals(4L, users.get(0).getId());
        assertEquals("oleg", users.get(0).getName());
    }
}