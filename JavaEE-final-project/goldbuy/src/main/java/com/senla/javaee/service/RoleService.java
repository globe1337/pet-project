package com.senla.javaee.service;

import com.senla.javaee.dto.role.RoleInfoDto;
import com.senla.javaee.dto.role.RoleWitUsersDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleService {
    RoleInfoDto delete(Long id);

    RoleInfoDto create(RoleInfoDto roleDto);

    RoleInfoDto update(RoleInfoDto roleDto);

    RoleInfoDto getById(Long id);

    RoleWitUsersDto getRoleWithUsers(Long id);

}
