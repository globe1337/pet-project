package com.senla.javaee.dao;

import com.senla.javaee.entity.User;

import java.util.List;

public interface UserDao  extends GenericDao<User,Long> {
    User getUserWithCredentials(Long id);

    User getUserWithProducts(Long id);

    User getUserWithRole(Long id);

    User getUserWithLogin(String login);

    List<User> getAllAdmin();

    User getByLoginWithRolesAndCredentials(String login);

    User getByNameWithRoles(String username);
}
