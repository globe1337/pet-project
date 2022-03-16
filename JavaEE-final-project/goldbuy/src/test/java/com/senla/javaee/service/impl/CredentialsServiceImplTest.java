package com.senla.javaee.service.impl;

import com.senla.javaee.dao.CredentialsDao;
import com.senla.javaee.dto.credentials.CredentialsInfoDto;
import com.senla.javaee.dto.credentials.CredentialsWithUserDto;
import com.senla.javaee.entity.Credentials;
import com.senla.javaee.service.converter.CredentialsConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CredentialsServiceImplTest {
    private final String login = "root";
    private final String password = "admin";
    private final Long id = 1L;
    private final Credentials credentials = Credentials.builder().login(login).password(password).id(id).build();
    @Spy
    private ModelMapper mapper;
    @Spy
    @InjectMocks
    CredentialsConverter credentialsConverter;
    @InjectMocks
    private CredentialsServiceImpl credentialsService;
    @Mock
    private CredentialsDao credentialsDao;

    @Test
    public void testCreate() {
        when(credentialsDao.create(any())).thenReturn(credentials);
        CredentialsInfoDto credentialsInfoDto = credentialsService.create(CredentialsInfoDto.builder().login(login).password(password).build());
        assertEquals(id, credentialsInfoDto.getId());
        assertEquals(login, credentialsInfoDto.getLogin());
        assertEquals(password, credentialsInfoDto.getPassword());

    }

    @Test
    public void testDelete() {
        when(credentialsDao.delete(any())).thenReturn(credentials);
        CredentialsInfoDto credentialsInfoDto = credentialsService.delete(1L);
        assertEquals(id, credentialsInfoDto.getId());
        assertEquals(login, credentialsInfoDto.getLogin());
        assertEquals(password, credentialsInfoDto.getPassword());
    }

    @Test
    public void testUpdate() {
        when(credentialsDao.update(any())).thenReturn(credentials);
        CredentialsInfoDto credentialsInfoDto = credentialsService.update(CredentialsInfoDto.builder().login(login).password(password).build());
        assertEquals(id, credentialsInfoDto.getId());
        assertEquals(login, credentialsInfoDto.getLogin());
        assertEquals(password, credentialsInfoDto.getPassword());
    }

    @Test
    public void testGetById() {
        when(credentialsDao.getById(any())).thenReturn(credentials);
        CredentialsInfoDto credentialsInfoDto = credentialsService.getById(1L);
        assertEquals(id, credentialsInfoDto.getId());
        assertEquals(login, credentialsInfoDto.getLogin());
        assertEquals(password, credentialsInfoDto.getPassword());


    }

    @Test
    public void getCredentialsWithUser() {
        when(credentialsDao.getCredentialsWithUser(any())).thenReturn(credentials);
        CredentialsWithUserDto credentialsWithUserDto = credentialsService.getCredentialsWithUser(id);
        assertEquals(id, credentialsWithUserDto.getCredentialsInfoDto().getId());
        assertEquals(login, credentialsWithUserDto.getCredentialsInfoDto().getLogin());
        assertEquals(password, credentialsWithUserDto.getCredentialsInfoDto().getPassword());
    }

}