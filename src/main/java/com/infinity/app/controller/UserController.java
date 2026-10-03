package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.model.AppRole;
import com.infinity.app.model.AppUser;
import com.infinity.app.service.AppRoleService;
import com.infinity.app.service.AppUserService;

@RestController
@RequestMapping("/atm/user")
public class UserController {

	private final AppRoleService appRoleService;
	private final AppUserService appUserService;
	
	public UserController(AppRoleService appRoleService,AppUserService appUserService) {
		this.appRoleService=appRoleService;
		this.appUserService=appUserService;
	}
	
	@GetMapping("/role")
	public ResponseEntity<List<AppRole>> getRoles(){
		System.out.println(appRoleService.getAllRoles());
        return ResponseEntity.ok(appRoleService.getAllRoles());
	}
	
	@PostMapping
	public ResponseEntity<AppUser> createUser(@RequestBody AppUser createdAppUser){
		return ResponseEntity.ok(appUserService.save(createdAppUser));
	}
}
