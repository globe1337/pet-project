package com.senla.javaee.security.service;

import com.senla.javaee.dao.UserDao;
import com.senla.javaee.security.entity.UserDetailsWithId;
import com.senla.javaee.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetailsWithId loadUserByUsername(String username) throws UsernameNotFoundException {
        var us = Optional.ofNullable(userDao.getByLoginWithRolesAndCredentials(username))
                .orElseThrow(() -> new UserNotFoundException(username));
        return new UserDetailsWithId(us.getId(),
                us.getCredentials().getLogin()
                , us.getCredentials().getPassword()
                , us.getRoles().stream().map(role ->
                new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList()));


    }
}
