package com.infinity.app.repo;

import com.infinity.app.model.RoleAuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleAuditLogRepository extends JpaRepository<RoleAuditLog,Long> {
	
	Page<RoleAuditLog> findByTargetUserId(Long targetUserId, Pageable pageable);

}
