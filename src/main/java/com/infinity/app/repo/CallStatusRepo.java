package com.infinity.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.CallStatus;

@Repository
public interface CallStatusRepo extends JpaRepository<CallStatus, Long>{
	@Query("SELECT l.id FROM LogStatus l WHERE l.status_desc = :desc")
    Long findIdByDesc(String desc);
}
