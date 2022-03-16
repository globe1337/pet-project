package com.senla.javaee.dao.impl;

import com.senla.javaee.dao.CredentialsDao;
import com.senla.javaee.configuration.GraphConfiguration;
import com.senla.javaee.entity.Credentials;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CredentialsDaoImpl extends AbstractDao<Credentials, Long> implements CredentialsDao {

    public CredentialsDaoImpl() {
        super(Credentials.class);
    }

    @Override
    public Credentials getCredentialsWithUser(Long id) {
        EntityGraph userGraph = entityManager.getEntityGraph(GraphConfiguration.CREDENTIALS_USER);
        Map hints = new HashMap();
        hints.put(GRAPH_PERSISTENCE, userGraph);
        return entityManager.find(Credentials.class, id, hints);
    }

    @Override
    public Credentials getByUserId(long id) {
        return entityManager.createQuery
                        ("select credentials from Credentials credentials left join fetch credentials.user user where user.id= :id", Credentials.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Credentials deleteByUserId(long id) {
        return entityManager.createQuery
                        ("delete credentials from Credentials credentials left join fetch credentials.user user where user.id= :id", Credentials.class)
                .setParameter("id", id).getSingleResult();
    }
}
