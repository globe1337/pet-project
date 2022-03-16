package com.senla.javaee.service;

import com.senla.javaee.dto.credentials.CredentialsInfoDto;
import com.senla.javaee.dto.credentials.CredentialsWithUserDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CredentialsService {
    CredentialsInfoDto delete(Long id);

    CredentialsInfoDto create(CredentialsInfoDto credentialsDto);

    CredentialsInfoDto update(CredentialsInfoDto credentialsDto);

    CredentialsInfoDto getById(Long id);

    CredentialsWithUserDto getCredentialsWithUser(Long id);

    CredentialsInfoDto getByUserId(long id);

    CredentialsInfoDto deleteByUserId(long id);
}
