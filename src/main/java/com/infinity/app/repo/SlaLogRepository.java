package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinity.app.model.SlaLog;
import com.infinity.app.model.SlaStatus;

public interface SlaLogRepository extends JpaRepository<SlaLog, Long> {
    List<SlaLog> findByStatus(SlaStatus status);
    List<SlaLog> findByFault_Id(Long faultId);
}
