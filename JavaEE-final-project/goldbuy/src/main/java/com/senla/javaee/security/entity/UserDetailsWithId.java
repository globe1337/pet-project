package com.senla.javaee.security.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserDetailsWithId extends User {
    private final long id;

    public UserDetailsWithId(long id, String login, String password, List<SimpleGrantedAuthority> collect) {
        super(login, password, collect);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
