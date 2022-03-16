package com.senla.javaee.dto.credentials;

import com.senla.javaee.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialsWithUserDto {
    private CredentialsInfoDto credentials;
    private UserInfoDto user;

    public CredentialsInfoDto getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInfoDto credentials) {
        this.credentials = credentials;
    }

    public UserInfoDto getUser() {
        return user;
    }

    public void setUser(UserInfoDto user) {
        this.user = user;
    }

    public CredentialsInfoDto getCredentialsInfoDto() {
        return credentials;
    }

    public void setCredentialsInfoDto(CredentialsInfoDto credentials) {
        this.credentials = credentials;
    }


}
