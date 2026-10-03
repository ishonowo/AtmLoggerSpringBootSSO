package com.infinity.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infinity.app.model.AppRole;
import com.infinity.app.repo.AppRoleRepository;

@Service
public class AppRoleService {
	
	private final AppRoleRepository appRoleRepo;
	
	public AppRoleService(AppRoleRepository appRoleRepo) {
        this.appRoleRepo = appRoleRepo;
    }
		
	public List<AppRole> getAllRoles() {
        return appRoleRepo.findAllRolesOrdered();
    }

}
