package com.senla.javaee.dao;

import com.senla.javaee.entity.Credentials;

public interface CredentialsDao extends GenericDao<Credentials, Long> {

    Credentials getCredentialsWithUser(Long id);

    Credentials getByUserId(long id);

    Credentials deleteByUserId(long id);
}
