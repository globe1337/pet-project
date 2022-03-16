package com.senla.javaee.dao;

import com.senla.javaee.entity.Role;

import java.util.List;

public interface RoleDao extends GenericDao<Role, Long> {

    Role getRoleWithUsers(Long id);

    List<Role> getUser();

    Role getModerator();
}
