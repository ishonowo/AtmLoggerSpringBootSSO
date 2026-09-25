package com.infinity.app.repo;

import com.infinity.app.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByLdapCn(String ldapCn);
}
