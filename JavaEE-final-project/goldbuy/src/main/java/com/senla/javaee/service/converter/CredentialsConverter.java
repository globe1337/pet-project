package com.senla.javaee.service.converter;

import com.senla.javaee.dto.credentials.CredentialsInfoDto;
import com.senla.javaee.dto.credentials.CredentialsWithUserDto;
import com.senla.javaee.entity.Credentials;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CredentialsConverter {
    @Autowired
    private ModelMapper mapper;

    public Credentials convertCreate(CredentialsInfoDto credentialsInfoDto, PasswordEncoder passwordEncoder) {
        credentialsInfoDto.setPassword(passwordEncoder.encode(credentialsInfoDto.getPassword()));
        return mapper.map(credentialsInfoDto, Credentials.class);
    }
    public Credentials convert(CredentialsInfoDto credentialsInfoDto) {
        return mapper.map(credentialsInfoDto, Credentials.class);
    }

    public CredentialsInfoDto convert(Credentials credentials) {
        return mapper.map(credentials, CredentialsInfoDto.class);
    }

    public Credentials convert(CredentialsWithUserDto credentialsInfoDto) {
        return mapper.map(credentialsInfoDto, Credentials.class);
    }

    public CredentialsWithUserDto covert(Credentials credentials) {
        return mapper.map(credentials, CredentialsWithUserDto.class);
    }
}
