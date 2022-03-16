package com.senla.javaee.service;

import com.senla.javaee.dto.user.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {

    UserInfoDto delete(Long id);

    UserInfoDto create(UserInfoDto userDto);

    UserInfoDto update(UserInfoDto userDto);

    UserInfoDto getById(Long id);

    UserWithCredentialsDto getUserWithCredentials(Long id);

    UserWithProductsDto getUserWithProducts(Long id);

    UserWithRolesDto getUserWithRole(Long id);

    UserInfoDto getUserWithLogin(String login);

    List<UserInfoDto> getAllAdmin();

    UserWithRolesDto getByNameWithRoles(String username);


    UserWithCredentialsDto registration(UserRegistrationDto userRegistrationDto);

    UserInfoDto setManager(Long userId);
}
