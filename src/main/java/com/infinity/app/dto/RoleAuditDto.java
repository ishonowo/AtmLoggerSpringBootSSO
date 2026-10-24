package com.infinity.app.dto;

import com.infinity.app.model.RoleAuditAction;

import java.time.Instant;

public record RoleAuditDto(
        Long id,
        Instant performedAt,
        String performedBy,
        RoleAuditAction action,
        Long targetUserId,
        String targetLdapCn,
        Long roleId,
        String roleName,
        String detail) {

}
