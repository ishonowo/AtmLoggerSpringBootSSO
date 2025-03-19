package com.infinity.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinity.app.model.LogStatus;

public interface LogStatusRepo extends JpaRepository<LogStatus, Long>{

}
