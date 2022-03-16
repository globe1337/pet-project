package com.senla.javaee.dto.user;

import com.senla.javaee.dto.credentials.CredentialsInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithCredentialsDto {
    private UserInfoDto user;
    private CredentialsInfoDto credentials;

    public UserInfoDto getUser() {
        return user;
    }

    public void setUser(UserInfoDto user) {
        this.user = user;
    }

    public CredentialsInfoDto getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInfoDto credentials) {
        this.credentials = credentials;
    }
}
