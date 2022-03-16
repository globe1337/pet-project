package com.senla.javaee.controller;


import com.senla.javaee.dto.credentials.CredentialsInfoDto;
import com.senla.javaee.security.entity.UserDetailsWithId;
import com.senla.javaee.service.CredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/credentials")
public class CredentialsController {

    private final CredentialsService credentialsService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public CredentialsInfoDto createCredentials(@RequestBody CredentialsInfoDto credentialsInfoDto) {
        return credentialsService.create(credentialsInfoDto);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CredentialsInfoDto getById(@PathVariable Long id) {
        return credentialsService.getById(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CredentialsInfoDto updateCredentials(@RequestBody CredentialsInfoDto credentialsInfoDto) {
        return credentialsService.update(credentialsInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CredentialsInfoDto deleteCredentials(@PathVariable Long id) {
        return credentialsService.delete(id);
    }


    @GetMapping(value = "/my")
    public CredentialsInfoDto getByUserId(@AuthenticationPrincipal UserDetailsWithId userDetailsWithId) {
        return credentialsService.getByUserId(userDetailsWithId.getId());
    }

    @PutMapping(value = "/update-my")
    public CredentialsInfoDto updateMyCredentials(@AuthenticationPrincipal UserDetailsWithId userDetailsWithId,
                                                  @RequestBody CredentialsInfoDto credentialsInfoDto) {
        credentialsInfoDto.setId(credentialsService.getByUserId(userDetailsWithId.getId()).getId());
        return credentialsService.update(credentialsInfoDto);
    }

//    @DeleteMapping(value = "/my}")
//    public CredentialsInfoDto deleteCredentials(@AuthenticationPrincipal UserDetailsWithId userDetailsWithId) {
//        return credentialsService.deleteByUserId(userDetailsWithId.getId());
//    }



}
