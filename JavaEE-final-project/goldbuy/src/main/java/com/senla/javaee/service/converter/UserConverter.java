package com.senla.javaee.service.converter;

import com.senla.javaee.dto.user.*;
import com.senla.javaee.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper mapper;

    public User convert(UserInfoDto userInfoDto) {
        return mapper.map(userInfoDto, User.class);
    }

    public UserInfoDto convert(User user) {
        return mapper.map(user, UserInfoDto.class);
    }

    public User convert(UserWithProductsDto userWithProductsDto) {
        return mapper.map(userWithProductsDto, User.class);
    }

    public UserWithProductsDto convertWithProduct(User user) {
        return mapper.map(user, UserWithProductsDto.class);
    }

    public UserWithRolesDto convertWithRoles(User user) {
        return mapper.map(user, UserWithRolesDto.class);
    }

    public List<UserInfoDto> convert(List<User> users) {
        return users.stream().
                map(user -> mapper.map(user, UserInfoDto.class))
                .collect(Collectors.toList());
    }

    public UserWithCredentialsDto convertWithCredentials(User user) {
        return mapper.map(user, UserWithCredentialsDto.class);
    }


    public User convertRegistration(UserRegistrationDto userRegistrationDto) {
        return mapper.map(userRegistrationDto,User.class);
    }

    public User convertWithCredentialsToUser(UserWithCredentialsDto userWithCredentialsDto) {
        return  mapper.map(userWithCredentialsDto,User.class);
    }
}