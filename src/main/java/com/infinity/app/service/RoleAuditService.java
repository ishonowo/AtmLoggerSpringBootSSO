package com.infinity.app.service;

import com.infinity.app.dto.RoleAuditDto;
import com.infinity.app.model.AppRole;
import com.infinity.app.model.AppUser;
import com.infinity.app.model.RoleAuditAction;
import com.infinity.app.model.RoleAuditLog;
import com.infinity.app.repo.RoleAuditLogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleAuditService {

    private static final Logger log = LoggerFactory.getLogger(RoleAuditService.class);
    private static final int MAX_PAGE_SIZE = 100;

    private final RoleAuditLogRepository repo;

    public RoleAuditService(RoleAuditLogRepository repo) {
        this.repo = repo;
    }

    /**
     * Joins the caller's transaction: if the role change rolls back, so does
     * this row, and a change can never exist without its audit record.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void recordSuccess(String actor, RoleAuditAction action,
                              AppUser target, AppRole role) {
        repo.save(new RoleAuditLog(actor, action, target, role, null));
        log.info("ROLE_AUDIT actor={} action={} target={} role={}",
                actor, action, target.getLdapCn(), role.getName());
    }

    /**
     * Runs in its own transaction so it still commits when the caller then
     * throws and its own transaction rolls back.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void recordDenied(String actor, AppUser target, AppRole role, String reason) {
        repo.save(new RoleAuditLog(actor, RoleAuditAction.REMOVE_DENIED, target, role, reason));
        log.warn("ROLE_AUDIT actor={} action=REMOVE_DENIED target={} role={} reason={}",
                actor, target.getLdapCn(), role.getName(), reason);
    }

    @Transactional(readOnly = true)
    public Page<RoleAuditDto> find(Long userId, int page, int size) {
        PageRequest pageable = PageRequest.of(
                Math.max(page, 0),
                Math.min(Math.max(size, 1), MAX_PAGE_SIZE),
                Sort.by(Sort.Direction.DESC, "performedAt", "id"));

        Page<RoleAuditLog> result = (userId == null)
                ? repo.findAll(pageable)
                : repo.findByTargetUserId(userId, pageable);

        return result.map(a -> new RoleAuditDto(
                a.getId(), a.getPerformedAt(), a.getPerformedBy(), a.getAction(),
                a.getTargetUserId(), a.getTargetLdapCn(),
                a.getRoleId(), a.getRoleName(), a.getDetail()));
    }
}
