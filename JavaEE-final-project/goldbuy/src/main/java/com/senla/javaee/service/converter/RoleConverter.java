package com.senla.javaee.service.converter;

import com.senla.javaee.dto.role.RoleInfoDto;
import com.senla.javaee.dto.role.RoleWitUsersDto;
import com.senla.javaee.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    @Autowired
    private ModelMapper mapper;

    public Role convert(RoleInfoDto roleInfoDto) {
        return mapper.map(roleInfoDto, Role.class);
    }

    public RoleInfoDto convert(Role role) {
        return mapper.map(role, RoleInfoDto.class);
    }

    public Role convert(RoleWitUsersDto roleWitUsersDto) {
        return mapper.map(roleWitUsersDto, Role.class);
    }

    public RoleWitUsersDto covertWithUser(Role role) {
        return mapper.map(role, RoleWitUsersDto.class);
    }


}