package com.senla.javaee.service.impl;


import com.senla.javaee.dao.RoleDao;
import com.senla.javaee.dao.UserDao;
import com.senla.javaee.dto.credentials.CredentialsInfoDto;
import com.senla.javaee.dto.user.*;
import com.senla.javaee.entity.Credentials;
import com.senla.javaee.entity.User;
import com.senla.javaee.service.UserService;
import com.senla.javaee.service.converter.CredentialsConverter;
import com.senla.javaee.service.converter.UserConverter;
import com.senla.javaee.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Optional.ofNullable;


@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserConverter userConverter;
    private final CredentialsConverter credentialsConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;


    @Override
    public UserInfoDto delete(Long id) {
        return userConverter.convert(userDao.delete(id));
    }

    @Override
    public UserInfoDto create(UserInfoDto userDto) {
        User user = userConverter.convert(userDto);
        return userConverter.convert(userDao.create(user));
    }

    @Override
    public UserInfoDto update(UserInfoDto userDto) {
        User user = userConverter.convert(userDto);
        return userConverter.convert(userDao.update(user));
    }

    @Override
    public UserInfoDto getById(Long id) {
        final User user = ofNullable(userDao.getById(id))
                .orElseThrow(() -> new UserNotFoundException(id + ""));
        return userConverter.convert(user);
    }


    @Override
    public UserWithCredentialsDto getUserWithCredentials(Long id) {
        return userConverter.convertWithCredentials(userDao.getUserWithCredentials(id));

    }

    @Override
    public UserWithProductsDto getUserWithProducts(Long id) {
        return userConverter.convertWithProduct(userDao.getUserWithProducts(id));
    }

    @Override
    public UserWithRolesDto getUserWithRole(Long id) {
        return userConverter.convertWithRoles(userDao.getUserWithRole(id));

    }

    @Override
    public UserInfoDto getUserWithLogin(String login) {
        return userConverter.convert(userDao.getUserWithLogin(login));
    }

    @Override
    public List<UserInfoDto> getAllAdmin() {
        return userConverter.convert(userDao.getAllAdmin());
    }

    @Override
    public UserWithRolesDto getByNameWithRoles(String username) {
        return userConverter.convertWithRoles(userDao.getByNameWithRoles(username));
    }

    @Override
    public UserWithCredentialsDto registration(UserRegistrationDto userRegistrationDto) {
        Credentials credentials = credentialsConverter.convertCreate(
                CredentialsInfoDto.builder()
                        .password(userRegistrationDto.getPassword())
                        .login(userRegistrationDto.getLogin())
                        .build(),
                passwordEncoder);

        User user = userDao.create(User.builder()
                .roles(roleDao.getUser())
                .credentials(credentials)
                .phoneNumber(userRegistrationDto.getPhoneNumber())
                .name(userRegistrationDto.getName())
                .mail(userRegistrationDto.getMail())
                .build());
//        UserWithCredentialsDto userWithCredentialsDto =userConverter.convertWithCredentials(user);
        //todo password and login ***********
        return userConverter.convertWithCredentials(user);
    }

    @Override
    public UserInfoDto setManager(Long userId) {
        User user = userDao.getById(userId);
        user.getRoles().add(roleDao.getModerator());
        return userConverter.convert(userDao.update(user));
    }
}
