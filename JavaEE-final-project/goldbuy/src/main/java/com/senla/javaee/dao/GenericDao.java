package com.senla.javaee.dao;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GenericDao<Entity, Id> {
    Entity create(Entity entity);

    Entity getById(Id id);

    Entity update(Entity entity);

    Entity delete(Id id);


}
