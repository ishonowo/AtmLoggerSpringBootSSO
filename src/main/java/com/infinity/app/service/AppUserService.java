package com.infinity.app.service;

import com.infinity.app.model.AppUser;
import com.infinity.app.repo.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
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
}
