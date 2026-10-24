package com.infinity.app.service;

import com.infinity.app.dto.RoleDto;
import com.infinity.app.dto.UserDto;
import com.infinity.app.model.AppRole;
import com.infinity.app.model.AppUser;
import com.infinity.app.model.RoleAuditAction;
import com.infinity.app.repo.AppRoleRepository;
import com.infinity.app.repo.AppUserRepository;
import com.infinity.app.security.RoleNames;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

	private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final RoleAuditService roleAuditService;

    public AppUserService(AppUserRepository appUserRepository,AppRoleRepository appRoleRepository,
    		RoleAuditService roleAuditService) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository=appRoleRepository;
        this.roleAuditService=roleAuditService;
    }

    // Only an ACTIVE row counts as a valid app account. A successful LDAP
    // bind proves identity but not app access - this is what decides access.
    public Optional<AppUser> findActiveByLdapCn(String ldapCn) {
        return appUserRepository.findByLdapCn(ldapCn).filter(AppUser::isActive);
    }

    @Transactional
    public void recordLogin(AppUser user) {
        user.setLastLoginAt(Instant.now());
        appUserRepository.save(user);
    }

	public AppUser save(AppUser createdAppUser) {
		return appUserRepository.save(createdAppUser);
	}
	
    // ---------- Role management ----------

    @Transactional(readOnly = true)
    public List<UserDto> findAllUsers() {
        return appUserRepository.findAllWithRoles().stream().map(this::toDto).toList();
    }

    @Transactional
    public UserDto addRole(Long userId, Long roleId, String actingLdapCn) {
        AppUser user = loadUser(userId);
        AppRole role = loadRole(roleId);

        boolean alreadyHas = user.getRoles().stream()
                .anyMatch(r -> r.getId().equals(roleId));
        if (!alreadyHas) {              // idempotent: adding twice changes nothing
            user.getRoles().add(role);
            roleAuditService.recordSuccess(actingLdapCn, RoleAuditAction.ADDED, user, role);
        }
        return toDto(appUserRepository.save(user));
    }

    @Transactional
    public UserDto removeRole(Long userId, Long roleId, String actingLdapCn) {
        AppUser user = loadUser(userId);
        AppRole role = loadRole(roleId);

        boolean hasRole = user.getRoles().stream()
                .anyMatch(r -> r.getId().equals(roleId));
        if (!hasRole) {                 // idempotent: removing twice changes nothing
            return toDto(user);
        }

        if (RoleNames.ADMIN.equals(role.getName())) {
            if (user.getLdapCn().equalsIgnoreCase(actingLdapCn)) {
                deny(actingLdapCn, user, role,
                        "Attempted to remove own ADMIN role",
                        "You cannot remove your own ADMIN role.");
            }
            if (user.isActive()
                    && appUserRepository.countActiveByRoleName(RoleNames.ADMIN) <= 1) {
                deny(actingLdapCn, user, role,
                        "Attempted to remove the last active ADMIN",
                        "Cannot remove the last active ADMIN.");
            }
        }

        user.getRoles().removeIf(r -> r.getId().equals(roleId));
        roleAuditService.recordSuccess(actingLdapCn, RoleAuditAction.REMOVED, user, role);
        return toDto(appUserRepository.save(user));
    }

    // Logs the denied attempt in its own transaction, then rejects the request.
    private void deny(String actor, AppUser user, AppRole role,
                      String auditReason, String clientMessage) {
        roleAuditService.recordDenied(actor, user, role, auditReason);
        throw new ResponseStatusException(HttpStatus.CONFLICT, clientMessage);
    }

    private AppUser loadUser(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found: " + id));
    }

    private AppRole loadRole(Long id) {
        return appRoleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Role not found: " + id));
    }

    private UserDto toDto(AppUser u) {
        List<RoleDto> roles = u.getRoles().stream()
                .map(r -> new RoleDto(r.getId(), r.getName(), r.getDescription()))
                .sorted(Comparator.comparing(RoleDto::name))
                .toList();
        return new UserDto(u.getId(), u.getLdapCn(), u.getFullName(),
                u.getEmail(), u.isActive(), roles);
    }
	
}
