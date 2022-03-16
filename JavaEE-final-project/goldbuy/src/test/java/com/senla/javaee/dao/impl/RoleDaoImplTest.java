package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.DaoTest;
import com.senla.javaee.dao.RoleDao;
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

@ContextConfiguration(classes = RoleDaoImpl.class)
class RoleDaoImplTest extends DaoTest {
    @Resource
    RoleDao roleDao;

    @BeforeEach
    public void fillingTable() {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().name("oleg").id(1L).build());
        userList.add(User.builder().name("sasha").id(2L).build());
        Role role = Role.builder().name("admin").users(userList).build();
        Role role1 = Role.builder().name("user").build();
        Role role2 = Role.builder().name("moderator").build();
        roleDao.create(role);
        roleDao.create(role1);
        roleDao.create(role2);
    }

    @Test
    public void create() {
        Role role = Role.builder().name("guest").build();
        roleDao.create(role);
        Role role1 = roleDao.getById(4L);
        assertEquals(4L, role1.getId());
        assertEquals(role.getName(), role1.getName());

    }

    @Test
    public void getById() {
        Role role = roleDao.getById(1L);
        assertEquals(1L, role.getId());
        assertEquals("admin", role.getName());
    }

    @Test
    public void update() {
        Role role = roleDao.getById(2L);
        role.setName("gold_user");
        roleDao.update(role);
        Role role1 = roleDao.getById(2L);
        assertEquals(2L, role1.getId());
        assertEquals("gold_user", role1.getName());
    }

    @Test
    public void delete() {
        roleDao.delete(3L);
        assertNull(roleDao.getById(3L));
    }

    @Test
    public void getRoleWithUsers() {
        Role role = roleDao.getRoleWithUsers(1L);
        assertEquals(1L, role.getId());
        assertEquals("admin", role.getName());
        assertEquals("oleg", role.getUsers().get(0).getName());
        assertEquals("sasha", role.getUsers().get(1).getName());
        assertEquals(1L, role.getUsers().get(0).getId());
        assertEquals(2L, role.getUsers().get(1).getId());
    }

}