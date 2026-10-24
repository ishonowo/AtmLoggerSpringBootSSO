package com.infinity.app.dto;

import java.util.List;

public record UserDto(
        Long id,
        String ldapCn,
        String fullName,
        String email,
        boolean active,
        List<RoleDto> roles) {
}
