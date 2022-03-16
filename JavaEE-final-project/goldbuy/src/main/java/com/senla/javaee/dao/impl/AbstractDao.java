package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.GenericDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDao<Entity, Id> implements GenericDao<Entity, Id> {

    public static final String GRAPH_PERSISTENCE = "javax.persistence.fetchgraph";
    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<Entity> entityClass;
    public AbstractDao(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Entity create(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }
    @Override
    public Entity getById(Id id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public Entity update(Entity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Entity delete(Id id) {
        Entity entity = getById(id);
        entityManager.remove(entity);
        return entity;
    }
}
