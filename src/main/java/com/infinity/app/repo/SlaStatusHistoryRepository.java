package com.infinity.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinity.app.model.SlaStatusHistory;

public interface SlaStatusHistoryRepository extends JpaRepository<SlaStatusHistory, Long> {
    List<SlaStatusHistory> findBySlaLog_IdOrderByChangedAtAsc(Long slaLogId);
}
