package com.senla.javaee.controller;

import com.senla.javaee.dto.user.UserInfoDto;
import com.senla.javaee.dto.user.UserRegistrationDto;
import com.senla.javaee.dto.user.UserWithCredentialsDto;
import com.senla.javaee.dto.user.UserWithRolesDto;
import com.senla.javaee.security.entity.UserDetailsWithId;
import com.senla.javaee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserInfoDto createUser(@RequestBody UserInfoDto userInfoDto) {
        return userService.create(userInfoDto);

    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserInfoDto updateUser(@RequestBody UserInfoDto userInfoDto) {
        return userService.update(userInfoDto);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserInfoDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserInfoDto deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }


    @PostMapping(value = "/registration")
    public UserWithCredentialsDto registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        return userService.registration(userRegistrationDto);
    }


    @GetMapping(value = "/credentials/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserWithCredentialsDto getWithCredById(@PathVariable Long id) {
        return userService.getUserWithCredentials(id);
    }

//
//    public UserInfoDto updateYour(@AuthenticationPrincipal UserDetailsWithId userInf, @RequestBody UserInfoDto userInfoDto) {
//
//
//        return null;
//    }//todo

    @PutMapping(value = "/setManager/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserInfoDto setRoleManager(@RequestBody Long userId) {
        return userService.setManager(userId);
    }

    @GetMapping(value = "/{id}/roles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserWithRolesDto getByNameWithRoles() {
        return userService.getByNameWithRoles("test");
    }


    @DeleteMapping(value = "/myself")
    public UserInfoDto deleteYourUser(@AuthenticationPrincipal UserDetailsWithId userInf) {
        return userService.delete(userInf.getId());
    }

    @GetMapping(value = "/my-info")
    public UserInfoDto getYour(@AuthenticationPrincipal UserDetailsWithId userDetailsWithId) {
        return userService.getById(userDetailsWithId.getId());
    }
}
