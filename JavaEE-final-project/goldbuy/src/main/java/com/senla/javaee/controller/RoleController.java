package com.senla.javaee.controller;


import com.senla.javaee.dto.role.RoleInfoDto;
import com.senla.javaee.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoleInfoDto createRole(@RequestBody RoleInfoDto roleInfoDto) {
        return roleService.create(roleInfoDto);

    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoleInfoDto getById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoleInfoDto updateRole(@RequestBody RoleInfoDto roleInfoDto) {
        return roleService.update(roleInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoleInfoDto deleteRole(@PathVariable Long id) {
        return roleService.delete(id);
    }
}
