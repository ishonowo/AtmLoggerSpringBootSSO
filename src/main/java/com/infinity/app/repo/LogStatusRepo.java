package com.infinity.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinity.app.model.CallStatus;

@Repository
public interface LogStatusRepo extends JpaRepository<CallStatus, Long>{

}
