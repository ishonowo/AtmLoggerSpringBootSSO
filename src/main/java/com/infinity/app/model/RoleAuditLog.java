package com.infinity.app.model;

//import jakarta.persistence.*;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Append-only record of a role change. No setters and every column is
 * updatable=false, so an entry cannot be altered once written.
 */
@Entity
@Table(name = "role_audit_log")
public class RoleAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "performed_at", nullable = false, updatable = false)
    private Instant performedAt;

    @Column(name = "performed_by", nullable = false, updatable = false, length = 100)
    private String performedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 20)
    private RoleAuditAction action;

    @Column(name = "target_user_id", nullable = false, updatable = false)
    private Long targetUserId;

    @Column(name = "target_ldap_cn", nullable = false, updatable = false, length = 100)
    private String targetLdapCn;

    @Column(name = "role_id", nullable = false, updatable = false)
    private Long roleId;

    @Column(name = "role_name", nullable = false, updatable = false, length = 50)
    private String roleName;

    @Column(updatable = false, length = 300)
    private String detail;

    protected RoleAuditLog() {} // for JPA

    public RoleAuditLog(String performedBy, RoleAuditAction action,
                        AppUser target, AppRole role, String detail) {
        this.performedAt = Instant.now();
        this.performedBy = performedBy;
        this.action = action;
        this.targetUserId = target.getId();
        this.targetLdapCn = target.getLdapCn();
        this.roleId = role.getId();
        this.roleName = role.getName();
        this.detail = detail;
    }

    public Long getId() { return id; }
    public Instant getPerformedAt() { return performedAt; }
    public String getPerformedBy() { return performedBy; }
    public RoleAuditAction getAction() { return action; }
    public Long getTargetUserId() { return targetUserId; }
    public String getTargetLdapCn() { return targetLdapCn; }
    public Long getRoleId() { return roleId; }
    public String getRoleName() { return roleName; }
    public String getDetail() { return detail; }
}
