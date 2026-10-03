package com.infinity.app.repo;

import com.infinity.app.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByName(String name);
    
    @Query("SELECT r FROM AppRole r ORDER BY  r.name ASC")
    List<AppRole> findAllRolesOrdered();
}
