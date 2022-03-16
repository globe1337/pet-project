package com.senla.javaee.dao.impl;

import com.senla.javaee.configuration.GraphConfiguration;
import com.senla.javaee.dao.RoleDao;
import com.senla.javaee.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleDaoImpl extends AbstractDao<Role, Long> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role getRoleWithUsers(Long id) {
        EntityGraph userGraph = entityManager.getEntityGraph(GraphConfiguration.ROLE_USERS);
        Map hints = new HashMap();
        hints.put(GRAPH_PERSISTENCE, userGraph);
        return entityManager.find(Role.class, id, hints);
    }

    @Override
    public List<Role> getUser() {
        return entityManager.createQuery("select role from Role role where role.name='USER'", Role.class).getResultList();
    }

    @Override
    public Role getModerator() {
        return entityManager.createQuery("select role from Role role where role.name='MODERATOR'", Role.class).getSingleResult();
        }
}
