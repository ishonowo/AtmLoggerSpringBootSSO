package com.infinity.app.repo;

import com.infinity.app.model.AppUser;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByLdapCn(String ldapCn);

    // Fetches users and their roles in one query instead of one query per user
    @EntityGraph(attributePaths = "roles")
    @Query("SELECT DISTINCT u FROM AppUser u ORDER BY u.fullName ASC")
    List<AppUser> findAllWithRoles();

    @Query("""
           SELECT COUNT(DISTINCT u) FROM AppUser u JOIN u.roles r
           WHERE r.name = :roleName AND u.active = true
           """)
    long countActiveByRoleName(@Param("roleName") String roleName);
}
