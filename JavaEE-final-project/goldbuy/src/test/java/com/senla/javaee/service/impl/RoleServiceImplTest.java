package com.senla.javaee.service.impl;

import com.senla.javaee.dao.RoleDao;
import com.senla.javaee.dto.role.RoleInfoDto;
import com.senla.javaee.dto.role.RoleWitUsersDto;
import com.senla.javaee.entity.Role;
import com.senla.javaee.entity.User;
import com.senla.javaee.service.converter.RoleConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    private final Long id = 1L;
    private final String roleName = "ADMIN";
    private final String userName = "oleg";
    private final String mail = "oleg@gmail.com";
    private final String phoneNumber = "123123";
    private final Role role = Role.builder().id(id).name(roleName).build();
    private final User user = User.builder().id(id).name(userName).mail(mail).
            phoneNumber(phoneNumber).build();
    @Spy
    private ModelMapper mapper;
    @Spy
    @InjectMocks
    private RoleConverter roleConverter;
    @InjectMocks
    private RoleServiceImpl roleService;
    @Mock
    private RoleDao roleDao;

    @Test
    public void testCreate() {
        when(roleDao.create(any())).thenReturn(role);
        RoleInfoDto roleInfoDto = roleService.create(RoleInfoDto
                .builder().name(roleName).build());
        assertEquals(id, roleInfoDto.getId());
        assertEquals(roleName, roleInfoDto.getName());
    }

    @Test
    public void testDelete() {
        when(roleDao.delete(any())).thenReturn(role);
        RoleInfoDto roleInfoDto = roleService.delete(id);
        assertEquals(id, roleInfoDto.getId());
        assertEquals(roleName, roleInfoDto.getName());
    }

    @Test
    public void testUpdate() {
        when(roleDao.update(any())).thenReturn(role);
        RoleInfoDto roleInfoDto = roleService.update(RoleInfoDto
                .builder().name(roleName).build());
        assertEquals(id, roleInfoDto.getId());
        assertEquals(roleName, roleInfoDto.getName());
    }

    @Test
    public void testGetById() {
        when(roleDao.getById(any())).thenReturn(role);
        RoleInfoDto roleInfoDto = roleService.getById(id);
        assertEquals(id, roleInfoDto.getId());
        assertEquals(roleName, roleInfoDto.getName());
    }

    @Test
    public void getRoleWithUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        role.setUsers(userList);
        when(roleDao.getRoleWithUsers(any())).thenReturn(role);
        RoleWitUsersDto roleWithUsers = roleService.getRoleWithUsers(id);
        assertEquals(id, roleWithUsers.getUsers().get(0).getId());
        assertEquals(phoneNumber, roleWithUsers.getUsers().get(0).getPhoneNumber());
        assertEquals(userName, roleWithUsers.getUsers().get(0).getName());
        assertEquals(mail, roleWithUsers.getUsers().get(0).getMail());
        assertEquals(id, roleWithUsers.getRole().getId());
        assertEquals(roleName, roleWithUsers.getRole().getName());
    }


}