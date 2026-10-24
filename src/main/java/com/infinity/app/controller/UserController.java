package com.infinity.app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.UserDto;
import com.infinity.app.dto.RoleAuditDto;
import com.infinity.app.model.AppRole;
import com.infinity.app.model.AppUser;
import com.infinity.app.service.AppRoleService;
import com.infinity.app.service.AppUserService;
import com.infinity.app.service.RoleAuditService;

@RestController
@RequestMapping("/atm/user")
public class UserController {

	private final AppRoleService appRoleService;
	private final AppUserService appUserService;
	private final RoleAuditService roleAuditService;
	
	public UserController(AppRoleService appRoleService,AppUserService appUserService,RoleAuditService roleAuditService) {
		this.appRoleService=appRoleService;
		this.appUserService=appUserService;
		this.roleAuditService=roleAuditService;
	}
	
	@GetMapping("/role")
	public ResponseEntity<List<AppRole>> getRoles(){
		System.out.println(appRoleService.getAllRoles());
        return ResponseEntity.ok(appRoleService.getAllRoles());
	}
	
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(appUserService.findAllUsers());
    }
	
	@PostMapping
	public ResponseEntity<AppUser> createUser(@RequestBody AppUser createdAppUser){
		return ResponseEntity.ok(appUserService.save(createdAppUser));
	}
	
    // Add a role (idempotent)
    @PutMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> addRole(@PathVariable Long userId,
                                           @PathVariable Long roleId,
                                           Authentication authentication) {
        return ResponseEntity.ok(
                appUserService.addRole(userId, roleId, authentication.getName()));
    }

    // Remove a role (idempotent)
    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> removeRole(@PathVariable Long userId,
                                              @PathVariable Long roleId,
                                              Authentication authentication) {
        return ResponseEntity.ok(
                appUserService.removeRole(userId, roleId, authentication.getName()));
    }

    // Role change history, newest first. Optional ?userId= filters to one person.
    @GetMapping("/audit")
    public ResponseEntity<Page<RoleAuditDto>> getRoleAudit(
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(roleAuditService.find(userId, page, size));
    }
}
